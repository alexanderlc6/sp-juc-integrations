package com.sp.proxy;

import com.sp.proxy.trans.TransactionProxy;
import org.junit.jupiter.api.Test;

/**
 * 事务处理JDK动态代理类测试
 *
 * @author luchao Created in 6/29/22 12:55 AM
 */
public class TransactionProxyTest {
    @Test
    public void testProxy(){
        UserDao userDao = new UserDaoImpl();
        UserDao proxyInstance = (UserDao) new TransactionProxy(userDao).genProxyInstance();
        proxyInstance.insert();
    }
}
