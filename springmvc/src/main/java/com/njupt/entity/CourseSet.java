package com.njupt.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator.
 */
public class CourseSet {
    private Set<Course> courses = new HashSet<Course>();

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    //Set必须在构造函数中先添加两个值
    public CourseSet(){
        courses.add(new Course());
        courses.add(new Course());
    }
}
