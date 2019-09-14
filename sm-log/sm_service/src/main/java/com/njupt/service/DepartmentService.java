package com.njupt.service;

import com.njupt.entity.Department;

import java.util.List;

public interface DepartmentService {

    //insert
    void add(Department department);

    //update
    void edit(Department department);

    //delete
    void remove(int id);

    //select  where id=..
    Department get(int id);

    //select *
    List<Department> getAll();
}
