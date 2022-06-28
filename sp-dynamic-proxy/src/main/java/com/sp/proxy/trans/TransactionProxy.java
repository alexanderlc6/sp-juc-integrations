package com.sp.proxy.trans;

import java.lang.reflect.Proxy;

/**
 * 事务处理代理类
 *
 * @author luchao Created in 6/29/22 12:51 AM
 */
public class TransactionProxy {

    private Object target;

    public TransactionProxy(Object target) {
        this.target = target;
    }

    public Object genProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("Start Transaction");
                    Object result = method.invoke(target, args);
                    System.out.println("Submit Transaction");
                    return result;
                });
    }
}
