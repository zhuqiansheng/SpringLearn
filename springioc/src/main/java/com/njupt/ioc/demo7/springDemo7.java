package com.njupt.ioc.demo7;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class springDemo7 {
    @Test
    public void demo1(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Bean1 bean1 = (Bean1) applicationContext.getBean("bean1");
        bean1.say();
        applicationContext.close();
    }
}
