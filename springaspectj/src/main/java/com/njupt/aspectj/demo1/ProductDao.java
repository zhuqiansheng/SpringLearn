package com.njupt.aspectj.demo1;

import org.springframework.stereotype.Component;

@Component("productDao")           //需先开启扫描
public class ProductDao {
    public void save(){
        System.out.println("保存商品...");
    }

    public String update(){
        System.out.println("修改商品...");
        return "hello";
    }

    public String delete(){
        System.out.println("删除商品...");
        return "删除成功";
    }

    public void findOne(){
        System.out.println("查询一个商品...");
//        int i = 1/0;
    }

    public void findAll(){
        System.out.println("查询所有商品...");
//        int j = 1/0;
    }

}
