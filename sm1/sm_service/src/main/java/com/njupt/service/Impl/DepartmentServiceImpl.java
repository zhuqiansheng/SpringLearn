package com.njupt.service.Impl;

import com.njupt.dao.DepartmentDao;
import com.njupt.entity.Department;
import com.njupt.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Qualifier("departmentDao")
    @Autowired

    private DepartmentDao departmentDao;    //自动注入

    public void add(Department department) {
        departmentDao.insert(department);
    }

    public void edit(Department department) {
        departmentDao.update(department);
    }

    public void remove(int id) {
        departmentDao.delete(id);
    }

    public Department get(int id) {
        return departmentDao.selectById(id);
    }

    public List<Department> getAll() {
        return departmentDao.selectAll();
    }
}
