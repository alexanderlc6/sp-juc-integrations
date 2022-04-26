package com.sp.juc.consume.model;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @description:
 * @author: luchao
 * @date: Created in 12/5/21 6:05 PM
 */
public class Consumer implements Runnable {
    private BlockingQueue<SpData> queue;
    private static final int SLEEP_TIME = 1000;

    public Consumer(BlockingQueue<SpData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("Start consumer id=" + Thread.currentThread().getId());
        Random r = new Random();

        try {
            while (true){
                SpData data = queue.take();
                if(null != data){
                    int result = data.getValue() * data.getValue();
                    System.out.println(MessageFormat.format(
                            "{0}*{1}={2}", data.getValue(), data.getValue(), result));
                    Thread.sleep(r.nextInt(SLEEP_TIME));
                }
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
