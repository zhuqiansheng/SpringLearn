package com.njupt.entity;


/**
 * create table if not exists department(
 *   id int primary key auto_increment,
 *   name varchar(20) not null,
 *   address varchar(20) not null
 * );
 */
public class Department {
    private int id;
    private String name;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
