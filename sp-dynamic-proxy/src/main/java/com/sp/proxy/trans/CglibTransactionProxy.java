package com.sp.proxy.trans;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * [Add Description Here]
 *
 * @author luchao Created in 6/29/22 1:00 AM
 */
public class CglibTransactionProxy implements MethodInterceptor {
    private Object target;

    public CglibTransactionProxy(Object target) {
        this.target = target;
    }

    public Object getProxyInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Start Transaction");
        Object result = methodProxy.invokeSuper(obj, args);
        System.out.println("Submit Transaction");
        return result;
    }
}
