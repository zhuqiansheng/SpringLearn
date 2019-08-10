package com.njupt.ioc.demo4;

public class User {
    private String username;
    private Integer age;

    public User(String username, Integer age) {
        this.username=username;
        this.age=age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
