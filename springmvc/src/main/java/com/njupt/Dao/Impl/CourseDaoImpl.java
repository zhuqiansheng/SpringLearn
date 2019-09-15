package com.njupt.Dao.Impl;

import com.njupt.Dao.CourseDao;
import com.njupt.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository("CourseDao")
public class CourseDaoImpl implements CourseDao {


    private Map<Integer, Course> courses = new HashMap<Integer, Course>();

    public void add(Course course) {
        courses.put(course.getId(), course);
    }

    public void delete(int id) {
        courses.remove(id);
    }

    public void update(Course course) {
        courses.put(course.getId(), course);
    }

    public Course select(int id) {
        return courses.get(id);
    }

    public Collection<Course> selectAll() {
        return courses.values();
    }
}
