package com.njupt.ioc.demo6;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("usersService")
public class UsersService {
//    @Value("rice")
//    private String food;

    public String sayHello(String name){
        return "Hello " + name;
    }

//    public void eat() {
//        System.out.println("eat " + this.food);
//    }
}
