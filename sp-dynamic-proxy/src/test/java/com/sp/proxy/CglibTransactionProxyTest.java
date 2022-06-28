package com.sp.proxy;

import com.sp.proxy.trans.CglibTransactionProxy;
import com.sp.proxy.trans.TransactionProxy;
import org.junit.jupiter.api.Test;

/**
 * 事务处理CGLIB动态代理类测试
 *
 * @author luchao Created in 6/29/22 12:55 AM
 */
public class CglibTransactionProxyTest {
    @Test
    public void testProxy(){
        UserDao userDao = new UserDaoImpl();
        UserDao proxyInstance = (UserDao) new CglibTransactionProxy(userDao).getProxyInstance();
        proxyInstance.insert();
    }
}
