package com.njupt.global;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 *核心控制器
 * 解析url ，bean的名字，method的名字，交给IOC
 */

public class DispatcherServlet extends GenericServlet {
    ApplicationContext applicationContext;

    @Override
    public void init() throws ServletException {
        super.init();
        //放在init里只会执行一次
        applicationContext =new  ClassPathXmlApplicationContext("spring.xml");
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;


//               /staff/add.do        login.do
//               staffController
//               public void add(HttpServletRequest request, HttpServletResponse response){}

        //path有两种形式  /staff/add.do        /login.do
        /**
         * 从path中解析出beanName和methodName
         */
        String path = request.getServletPath().substring(1);    //去掉前面的斜杠
        String beanName = null;
        String methodName = null;

        int index = path.indexOf('/');
        if (index != -1) {
            beanName = path.substring(0, index)+"Controller";       //规定统一用beanController的格式
            methodName = path.substring(index + 1, path.indexOf(".do"));    //.do前面的一部分为方法名
        }else{
            beanName = "selfController";
            methodName = path.substring(0, path.indexOf(".do"));
        }

        //根据beanName获取bean的对象
        Object obj = applicationContext.getBean(beanName);

        try {
            //用反射获取 bean对象的方法并执行
            Method method = obj.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //obj执行method方法，并带入 request 和 response
            method.invoke(obj, request, response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //然后配置web.xml

}
