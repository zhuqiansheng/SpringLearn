package com.njupt.service;

import com.njupt.entity.Staff;

import java.util.List;

public interface StaffService {

    //insert
    void add(Staff staff);

    //update
    void edit(Staff staff);

    //delete
    void remove(int id);

    //select  where id=..
    Staff get(int id);

    //select *
    List<Staff> getAll();
}
