package com.njupt.mybatis.interceptpor;


import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 插件签名，告诉mybatis当前插件用来拦截哪个对象的哪个方法
 */
@Intercepts({
    @Signature(type = ResultSetHandler.class, method = "handleResultSets",args = Statement.class)
        }
        )
public class MyFirstInteceptor implements Interceptor {

    //拦截目标对象的目标方法
    public Object intercept(Invocation invocation) {
        System.out.println("拦截的目标对象  "+invocation.getTarget());
        Object object = null;
        try {
            //执行原目标方法
            object=invocation.proceed();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }

    //包装目标对象 为目标对象创建代理对象
    public Object plugin(Object o) {
        System.out.println("将要包装的目标对象 "+o);
        return Plugin.wrap(o, this);
    }


    public void setProperties(Properties properties) {
        System.out.println("插件配置的初始化参数" + properties);
    }
}
