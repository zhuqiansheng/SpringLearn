package com.njupt.aop.demo4;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


public class MyAroundAdvice  implements MethodInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("环绕前置增强...");
        Object obj=invocation.proceed();      //执行这个被增强的方法
        System.out.println("环绕后置增强...");
        return obj;
    }
}
