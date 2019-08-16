package com.njupt.sc.dao.Impl;

import com.njupt.sc.dao.SelectionDao;
import com.njupt.sc.entity.Selection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SelectionDaoImpl implements SelectionDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //添加学生的选课信息
    public void insert(List<Selection> selections) {
        String sql = "insert into selection values(?,?,?,?)";
        List<Object[]> list = new ArrayList<Object[]>();
        for (Selection sel : selections) {
            Object[] args = new Object[4];
            args[0] = sel.getSid();
            args[1] = sel.getCid();
            args[2] = sel.getSelectTime();
            args[3] = sel.getScore();
            list.add(args);
        }
        jdbcTemplate.batchUpdate(sql, list);
    }

    //删除一条学生的选课信息
    public void delete(int sid, int cid) {
        String sql = "delete from selection where sid=? and cid=?";
        jdbcTemplate.update(sql, sid, cid);
    }

    /**
     * 根据学生id查询其选课信息，封装为map
     * @param sid
     * @return
     */
    public List<Map<String, Object>> selectByStudent(int sid) {
        String sql = "select se.*,stu.name as sname,cou.name as cname from selection as se" +
                "left join student as stu on se.student=stu.id" +
                "left join course as cou on se.course=cou.id" +
                "where student=?";
        return jdbcTemplate.queryForList(sql, sid);
    }

    public List<Map<String, Object>> selectByCourse(int cid) {
        String sql="select se.*,stu.name as sname,cou.name as cname from selection as se" +
                "left join student as stu on se.student=stu.id" +
                "left join course as cou on se.course=cou.id" +
                "where course=cid";
        return jdbcTemplate.queryForList(sql, cid);
    }
}
