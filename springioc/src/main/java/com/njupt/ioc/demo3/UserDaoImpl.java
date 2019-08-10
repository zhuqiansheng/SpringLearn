package com.njupt.ioc.demo3;

public class UserDaoImpl implements UserDao {

    public void findAll() {
        System.out.println("查询用户。");
    }

    public void save() {
        System.out.println("保存");

    }

    public void update() {
        System.out.println("修改");

    }

    public void delete() {
        System.out.println("删除");

    }
}
