package com.njupt.sc.dao;

import com.njupt.sc.entity.Student;

import java.util.List;

public interface StudentDao {
    void insert(Student student);

    void delete(int id);

    void update(Student student);

    Student select(int id);

    List<Student> selectAll();
}
