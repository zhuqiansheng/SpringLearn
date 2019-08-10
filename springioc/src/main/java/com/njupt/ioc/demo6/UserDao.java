package com.njupt.ioc.demo6;

import org.springframework.stereotype.Component;

@Component("userDao")
public class UserDao {
    public void save(){
        System.out.println("Dao中的save");
    }
}
