package com.sp.juc.consume.model.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledExecutor demo
 * 使用ScheduledThreadPool线程池异步处理:ScheduledFutureTask和阻塞队列DelayedWorkQueue(内部为PriorityQueue,deadline最先任务在队列头部优先执行)
 * @author luchao Created in 6/11/22 11:50 AM
 */
public class MyScheduledExecutor {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

    /**
     * 1s延迟后开始执行任务,每2s一次
     */
    public void execute(){
        executorService.scheduleAtFixedRate(() ->
                System.out.println("Hello"), 1000, 2000, TimeUnit.MILLISECONDS);
    }
}
