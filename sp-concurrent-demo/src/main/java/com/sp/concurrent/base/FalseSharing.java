package com.sp.concurrent.base;

/**
 * 测试避免伪共享的方法
 *
 * @author luchao Created in 11:35
 */
public class FalseSharing implements Runnable {
    public final static int NUM_THREADS = 4;
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private int arrayIndex = 0;
//    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];
//    private static VolatileLong2[] longs = new VolatileLong2[NUM_THREADS];
    private static VolatileLong3[] longs = new VolatileLong3[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
//            longs[i] = new VolatileLong();
//            longs[i] = new VolatileLong2();
            longs[i] = new VolatileLong3();
        }
    }

    public FalseSharing(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i){
            longs[arrayIndex].value = i;
        }
    }

    public static void main(String[] args) throws Exception {
        long start = System.nanoTime();
        runTest();
        System.out.println("duration=" + (System.nanoTime() - start));
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : threads){
            t.start();
        }

        for (Thread t : threads){
            t.join();
        }
    }

    public final static class VolatileLong {
        public volatile long value = 0L;
    }

    /**
     * Long padding避免伪共享
     */
    public final static class VolatileLong2 {
        volatile long p0,p1,p2,p3,p4,p5,p6;
        public volatile long value = 0L;
        volatile long q0,q1,q2,q3,q4,q5,q6;
    }

    /**
     * JDK8新特性:@Contented注解避免伪共享
     * Notice:需新增JVM启动参数：-XX:-RestrictContended才可以生效
     */
    @sun.misc.Contended
    public final static class VolatileLong3 {
        public volatile long value = 0L;
    }
}
