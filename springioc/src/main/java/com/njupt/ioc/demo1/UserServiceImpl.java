package com.njupt.ioc.demo1;

public class UserServiceImpl implements UserService{
    private String name;
    public void sayHello(){
        System.out.println("Hello Spring,"+this.name);
    }

    public void setName(String name) {
        this.name=name;
    }
}
