package com.njupt.aspectj.demo1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringDemo1 {
    @Resource(name="productDao")
    private ProductDao productDao;

    @Test
    public void demo1(){
        productDao.delete();
        productDao. findAll();
        productDao.save();
        productDao.findOne();
        String s=productDao.update();
        System.out.println(s);
    }
}
