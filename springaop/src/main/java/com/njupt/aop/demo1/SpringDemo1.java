package com.njupt.aop.demo1;

import org.junit.Test;

import java.lang.reflect.Proxy;

public class SpringDemo1 {
    @Test
    public void demo1(){
        UserDao userDao = new UserDaoImpl();
        UserDao proxy=(UserDao)new MyjdkProxy(userDao).CreateProxy();
        proxy.find();
        proxy.update();
        proxy.delete();
        proxy.save();
    }

}
