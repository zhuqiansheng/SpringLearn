package com.njupt.aop.demo2;

import org.junit.Test;

public class SpringDemo2 {
    @Test
    public void demo1(){
        ProductDao productDao=new ProductDao();
        //生成代理类
        ProductDao proxy=(ProductDao)new MyCglibProxy(productDao).createProxy();
        proxy.find();
        proxy.update();
        proxy.delete();
        proxy.save();
    }

}
