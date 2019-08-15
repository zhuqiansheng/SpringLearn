package com.njupt.aspectj.demo1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect            //注解这是切面类
public class MyAspectAnno {
    @Before(value="pointcut1()")
    //    com.njupt.aspectj.demo1包下ProductDao类的所有类型的所有方法
    public void mybefore(JoinPoint joinPoint){
        System.out.println("前置通知。。"+joinPoint);
    }

    @AfterReturning(value = "execution(* com.njupt.aspectj.demo1.ProductDao.delete(..)))",returning = "returning")
    public void afterReturning(Object returning){
        System.out.println("后置通知。。。"+returning);
    }

    @Around(value="execution(* com.njupt.aspectj.demo1.ProductDao.update(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕前通知。。。");
        Object obj=joinPoint.proceed();  //执行目标方法，如果不调用 ProceedingJoinPoint的 proceed方法，那么目标方法就被拦截了
        System.out.println("环绕后通知"+obj.toString());

        return obj;
    }

    @AfterThrowing(value ="execution(* com.njupt.aspectj.demo1.ProductDao.findOne(..))",throwing = "e")
    public void afterThrowing(Throwable e){
        System.out.println("异常抛出通知。。。"+e.getMessage());
    }

    @Pointcut(value="execution(* com.njupt.aspectj.demo1.ProductDao.save(..))")
    private void pointcut1(){}
}