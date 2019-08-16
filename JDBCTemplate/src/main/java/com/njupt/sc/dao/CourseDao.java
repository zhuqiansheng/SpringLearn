package com.njupt.sc.dao;

import com.njupt.sc.entity.Course;

import java.util.List;

public interface CourseDao {
    void insert(Course course);

    void delete(int id);

    void update(Course course);

    Course select(int id);

    List<Course> selectAll();
}
