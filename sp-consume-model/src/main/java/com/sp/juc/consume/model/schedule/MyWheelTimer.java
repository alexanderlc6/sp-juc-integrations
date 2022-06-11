package com.sp.juc.consume.model.schedule;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 时间轮定时器
 * 环形数组--时间轮Slot:HashedWheelBucket[]双向链表结构,每个bucket包含HashedWheelTimeout列表(Head和Tail节点用于环形双向遍历)。
 * 工作线程Worker负责从MpscQueue取出执行任务,waitForNextTick()计算任务等待时间deadline,通过固定间隔tickDuration推动
 * 处理被取消的任务:processCancelledTasks(), 位于cancelledTimeout队列,Worker会取出并从HashedWheelBucket删除任务
 * 缺点:
 * 1）任务为串行执行,前序任务执行过长会影响后续任务,可能产生任务堆积情况，所以只适合耗时较短的任务;
 * 2）空推进问题：例如Task A 1s后执行、Task B 6h后执行。
 * --->> Kafka已优化:
 * 1）通过DelayQueue保存时间轮每个bucket、根据bucket到期时间排序(最近时间在队头)。
 * 2）层级时间轮:TimerTaskLast当剩余n秒时触发降级,添加到上一层时间轮中(参考时钟时分秒针概念)：
 * Layer 1: 每个时间格Slot为1ms,整个时间轮跨度为20ms, Layer 2: 20ms,整个时间轮跨度400ms, Layer 3: 400ms,整个时间轮跨度8000ms,
 * e.g.任务A为450ms则在第三层时间轮第一格,当倒计时50ms时重新添加到第二层时间轮第3格,当倒计时40ms后再次触发降级放入第一层时间轮第一格.
 * 3)Worker为单线程,若某个任务执行过长会造成线程阻塞；
 * 4）相比于传统Timer,内存占用较大。
 *
 *
 * @author luchao Created in 6/11/22 12:05 PM
 */
public class MyWheelTimer {
    Timer timer = new HashedWheelTimer();

    public void execute() {
        //添加任务(线程为懒启动方式无需用户调用--在时间轮中无任务时避免工作线程空转造成CPU损耗)、计算任务deadline,
        //创建HashedWheelTimeout对象加入到MpscQueue(为保证时间轮线程安全性不直接添加到时间轮),
        // 而是在transferTimeoutsToBuckets()加入时间轮(timeout.poll()每次处理10w个),需在时间轮转动的圈数:timeout.remainingRounds,
        // 过期后会重放入HashedWheelBucket中
        Timeout timeout = timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("Timeout1:" + new Date());
            }
        }, 10, TimeUnit.SECONDS);

        if(!timeout.isExpired()){
            timeout.cancel();
        }

        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws InterruptedException {
                System.out.println("Timeout2:" + new Date());
                Thread.sleep(5000);
            }
        }, 1, TimeUnit.SECONDS);

        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws InterruptedException {
                System.out.println("Timeout3:" + new Date());
                Thread.sleep(5000);
            }
        }, 3, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        MyWheelTimer wheelTimer = new MyWheelTimer();
        wheelTimer.execute();
    }

}
