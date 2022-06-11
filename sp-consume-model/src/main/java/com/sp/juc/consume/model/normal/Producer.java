package com.sp.juc.consume.model.normal;

import com.sp.juc.consume.model.SpData;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 生产者
 * @author: luchao
 * @date: Created in 11/20/21 5:56 PM
 */
public class Producer implements Runnable {
    private volatile boolean isRunning = true;
    private BlockingQueue<SpData> queue;
    private static AtomicInteger count = new AtomicInteger();
    private static final int SLEEP_TIME = 1000;

    public Producer(BlockingQueue<SpData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        SpData data;
        Random r = new Random();

        System.out.println("Start producer id=" + Thread.currentThread().getId());
        try {
            while (isRunning){
                Thread.sleep(r.nextInt(SLEEP_TIME));
                data = new SpData(count.incrementAndGet());
                System.out.println(data + " is put into queue.");
                if(!queue.offer(data, 2, TimeUnit.SECONDS)){
                    System.out.println("Failed to put data:" + data);
                }
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    public void stop(){
        isRunning = false;
    }
}
