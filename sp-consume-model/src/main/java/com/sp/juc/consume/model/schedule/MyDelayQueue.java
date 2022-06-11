package com.sp.juc.consume.model.schedule;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列demo
 * 适用于接口调用失败或请求超时后重试(按最大次数或指数退避算法设置对象deadline 2s/4s/8s等)
 * 基础参考https://blog.csdn.net/Chenftli/article/details/122664538
 *
 * @author luchao Created in 6/11/22 11:22 AM
 */
public class MyDelayQueue {
    public void execute() throws InterruptedException {
        DelayQueue<SampleTask> delayQueue =  new DelayQueue();

        long now = System.currentTimeMillis();
        delayQueue.put(new SampleTask(now + 1000));
        delayQueue.put(new SampleTask(now + 2000));
        delayQueue.put(new SampleTask(now + 3000));

        for (int i = 0; i < 3; i++) {
            System.out.println(new Date(delayQueue.take().getExecTime()));
        }
    }

    public static class SampleTask implements Delayed {

        private Long execTime;
        public SampleTask(Long execTime){
            this.execTime = execTime;
        }

        public Long getExecTime() {
            return execTime;
        }

        /**
         * 优先级排序
         * @param o
         * @return
         */
        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }

        /**
         * 计算消息延迟的剩余时间
         * 仅当返回值<= 0时才能从队列中取出任务
         * @param unit
         * @return
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(execTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyDelayQueue myDelayQueue = new MyDelayQueue();
        myDelayQueue.execute();
    }
}
