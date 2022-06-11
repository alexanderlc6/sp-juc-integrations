package com.sp.netty.integrations.common;

import com.sp.netty.integrations.model.TradeOrder;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

/**
 * 优势:
 * 1）内部维护了一个InternalFastThreadLocalMap通过数组下标index查找,O(1)复杂度,无需JDK ThreadLocal会发生hash冲突。
 * 2）FastThreadLocal只需扩容2倍容量,原数据copy到新数组即可(原生需要rehash)
 * 3）安全性更高：原生内存泄露常常发生、只能主动检查来防止，而FastThreadLocal提供了remove()主动清除对象,
 * 还封装了FastThreadLocalRunnable会执行FastThreadLocal.removeAll(),清除了所有Set<FastThreadLocal>对象
 *
 * @author luchao Created in 6/11/22 10:42 AM
 */
public class MyFastThreadLocal {
    private static final FastThreadLocal<String> THREAD_NAME_LOCAL = new FastThreadLocal<>();
    private static final FastThreadLocal<TradeOrder> TRADE_THREAD_LOCAL = new FastThreadLocal<TradeOrder>(){
        @Override
        protected TradeOrder initialValue() throws Exception {
            return new TradeOrder(-1, "None");
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            int tradeId = i;
            String threadName = "thread" + i;
            new FastThreadLocalThread(() -> {
                THREAD_NAME_LOCAL.set(threadName);
                TradeOrder tradeOrder = new TradeOrder(tradeId, tradeId % 2 == 0 ? "Payed" : "Not payed");
                TRADE_THREAD_LOCAL.set(tradeOrder);

                System.out.println("thread name:" + THREAD_NAME_LOCAL.get());
                System.out.println("tradeOrderInfo:" + TRADE_THREAD_LOCAL.get());
            }, threadName).start();
        }
    }


}
