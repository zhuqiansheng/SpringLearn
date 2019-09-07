package com.njupt.dao;

import com.njupt.entity.Department;
import org.springframework.stereotype.Repository;

import javax.annotation.Resources;
import java.util.List;

@Repository("departmentDao")
public interface DepartmentDao {
    void insert(Department department);

    void delete(int id);

    void update(Department department);

    Department selectById(int id);

    List<Department> selectAll();
}
