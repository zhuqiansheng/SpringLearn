package com.njupt.sc.dao.Impl;

import com.njupt.sc.dao.StudentDao;
import com.njupt.sc.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(Student student) {
        String sql = "insert into student(name,sex,born) values(?,?,?) ";
        jdbcTemplate.update(sql, student.getName(), student.getSex(), student.getBorn());
        //jdbcTemplate会自动将Java.util.Date转换成Java.sql.Date
    }

    public void delete(int id) {
        String sql = "delete from student where id=?";
        jdbcTemplate.update(sql, id);
    }

    public void update(Student student) {
        String sql = "update student set name=?,sex=?,born=? where id=?";
        jdbcTemplate.update(sql, student.getName(), student.getSex(), student.getBorn(), student.getId());

    }
   //需要封装成类，所以需要实现RowMapper
    public Student select(int id) {
        String sql = "select * from student where id=?";
        return (Student) jdbcTemplate.queryForObject(sql, new StudentRowMapper(),id);
    }

    public List<Student> selectAll() {
        String sql = "select * from student";
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }

    private class StudentRowMapper implements RowMapper {
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student=new Student();
            student.setName(resultSet.getString("name"));
            student.setId(resultSet.getInt("id"));
            student.setSex(resultSet.getString("sex"));
            student.setBorn(resultSet.getDate("born"));
            return student;
        }
    }

}
