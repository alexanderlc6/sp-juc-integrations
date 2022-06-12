package com.sp.juc.consume.model.schedule;

import io.netty.util.internal.shaded.org.jctools.queues.MpscArrayQueue;

/**
 * 多生产者单消费者队列
 *
 * @author luchao Created in 6/11/22 12:41 PM
 */
public class MyMpscQueue {
    public static final MpscArrayQueue<String> MPSC_ARRAY_QUEUE = new MpscArrayQueue<>(2);

    public void execute(){
        for (int i = 0; i < 2; i++) {
            int index = i;
            new Thread(() -> MPSC_ARRAY_QUEUE.offer("data" + index), "thread" + index).start();
        }

        try {
            Thread.sleep(1000L);
            MPSC_ARRAY_QUEUE.add("data3");
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Queue size:" + MPSC_ARRAY_QUEUE.size() + ", Queue capacity:" + MPSC_ARRAY_QUEUE.capacity());
        System.out.println("Queue output with throw exception:" + MPSC_ARRAY_QUEUE.remove());
        System.out.println("Queue output with return NULL:" + MPSC_ARRAY_QUEUE.poll());
    }

    public static void main(String[] args) {
        MyMpscQueue mpscQueue = new MyMpscQueue();
        mpscQueue.execute();
    }
}
