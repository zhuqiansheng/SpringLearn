package com.njupt.sc.dao.Impl;

import com.njupt.sc.dao.CourseDao;
import com.njupt.sc.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void insert(Course course) {
        String sql = "insert into course(name,score) values(?,?)";
        jdbcTemplate.update(sql, course.getName(), course.getScore());
    }

    public void delete(int id) {
        String sql = "delete from course where id=?";
        jdbcTemplate.update(sql, id);
    }

    public void update(Course course) {
        String sql = "update course set name=?,score=? where id=?";
        jdbcTemplate.update(sql, course.getName(), course.getScore(), course.getId());
    }

    public Course select(int id) {
        String sql = "select * from course where id=?";
        return (Course) jdbcTemplate.queryForObject(sql, new CourseRowMapper(), id);
    }

    public List<Course> selectAll() {
        String sql = "select * from course";
        return jdbcTemplate.query(sql, new CourseRowMapper());
    }

    private class CourseRowMapper implements RowMapper {

        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Course course=new Course();
            course.setName(resultSet.getString("name"));
            course.setScore(resultSet.getInt("score"));
            course.setId(resultSet.getInt("id"));
            return course;
        }
    }
}
