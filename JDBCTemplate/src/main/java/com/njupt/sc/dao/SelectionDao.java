package com.njupt.sc.dao;

import com.njupt.sc.entity.Selection;

import java.util.List;
import java.util.Map;

public interface SelectionDao {
    //插入选课信息
    void insert(List<Selection> selections);

    //删除某个学生选的某门课
    void delete(int sid, int cid);

    //根据学生id，查询这个学生选的所有课程的信息
    List<Map<String, Object>> selectByStudent(int sid);

    //根据课程号，查询选了这门的所有学生的信息
    List<Map<String, Object>> selectByCourse(int cid);
}
