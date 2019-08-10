package com.njupt.aop.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 传统方式，jdk的动态代理
 */

public class MyjdkProxy implements InvocationHandler {
    private UserDao userDao;
    public MyjdkProxy(UserDao userDao) {
        this.userDao=userDao;
    }

    public Object CreateProxy(){
        /**
         * (类加载器，类接口，this)
         */
        Object proxy=Proxy.newProxyInstance(userDao.getClass().getClassLoader(), userDao.getClass().getInterfaces(), this);
        return proxy;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("save".equals(method.getName())) {           //如果是save方法，实现增强
            System.out.println("权限校验过程...");
            return method.invoke(userDao, args);
        }


        return method.invoke(userDao,args);
    }
}
