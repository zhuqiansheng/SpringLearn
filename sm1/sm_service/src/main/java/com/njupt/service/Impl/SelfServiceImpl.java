package com.njupt.service.Impl;

import com.njupt.dao.SelfDao;
import com.njupt.dao.StaffDao;
import com.njupt.entity.Staff;
import com.njupt.service.SelfService;
import org.springframework.stereotype.Service;

@Service("selfService")
public class SelfServiceImpl implements SelfService {

    private SelfDao selfDao;
    private StaffDao staffDao;

    public Staff login(String account, String password) {
        Staff staff = selfDao.selectByAccount(account);
        if (staff != null) {
            if (password == staff.getPassword()) {
                return staff;
            }
        }

        return null;
    }

    public void changePassword(Integer id, String password) {
        Staff staff=staffDao.selectById(id);
        staff.setPassword(password);
        staffDao.update(staff);

    }
}
