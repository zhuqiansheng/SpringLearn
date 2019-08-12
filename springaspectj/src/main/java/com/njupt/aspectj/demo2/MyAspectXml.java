package com.njupt.aspectj.demo2;

import org.aspectj.lang.JoinPoint;

public class MyAspectXml {
    public void before(JoinPoint joinPoint){
        System.out.println("xml方式的前置通知 "+joinPoint);
    }
}
