package com.njupt.ioc.demo1;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

//测试
public class SpringDemo1 {
    @Test
    /**
     * 传统方式开发
     */
    public void demo1(){
//        UserService userService=new UserServiceImpl();
        UserServiceImpl userService=new UserServiceImpl();      //必须是UserServiceImpl才可以调用setName方法
        userService.setName("张三");
        userService.sayHello();
    }

    @Test
    /**
     * spring方式实现
     */
    public void demo2(){
        //创建spring的工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类,字符串是applicationContext.xml中对应类的id
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.sayHello();

    }



    @Test
    /**
     * 读取磁盘系统中的配置文件
     */
    public void demo3() {
        //创建Spring的工厂类
        ApplicationContext  applicationContext=new FileSystemXmlApplicationContext("E:\\Coding\\Java\\web\\springioc\\src\\main\\resources\\applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.sayHello();
    }


}
