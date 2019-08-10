package com.njupt.aop.demo2;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


public class MyCglibProxy implements MethodInterceptor {
    private ProductDao productDao;
    public MyCglibProxy(ProductDao productDao){
        this.productDao=productDao;
    }
    public Object createProxy() {
        //创建核心类
        Enhancer enhancer=new Enhancer();
        //设置父类
        enhancer.setSuperclass(productDao.getClass());
        //设置回调
        enhancer.setCallback(this);
        //生成代理
        Object proxy=enhancer.create();
        return proxy;
    }

    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if ("save".equals(method.getName())) {
            System.out.println("权限校验过程...");
            return methodProxy.invokeSuper(proxy, args);
        }
        return methodProxy.invokeSuper(proxy, args);

    }
}
