package com.njupt.service.Impl;


import com.njupt.dao.StaffDao;
import com.njupt.entity.Staff;
import com.njupt.service.StaffService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("staffService")
public class StaffServiceImpl implements StaffService {

    private StaffDao staffDao;

    public void add(Staff staff) {
        //默认初始密码
        staff.setPassword("123456");
        staff.setWorkTime(new Date());
        staff.setStatus("正常");
        staffDao.insert(staff);
    }

    public void edit(Staff staff) {
        staffDao.update(staff);
    }

    public void remove(int id) {
        staffDao.delete(id);
    }

    public Staff get(int id) {
        return staffDao.selectById(id);
    }

    public List<Staff> getAll() {
        return staffDao.selectAll();
    }
}
