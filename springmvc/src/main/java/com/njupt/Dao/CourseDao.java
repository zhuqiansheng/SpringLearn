package com.njupt.Dao;

import com.njupt.entity.Course;

import java.util.Collection;


public interface CourseDao {

    public void add(Course course);

    public void delete(int id);

    public void update(Course course);

    public Course select(int id);

    public Collection<Course> selectAll();
}
