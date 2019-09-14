package com.njupt.dao;

import com.njupt.entity.Staff;
import org.springframework.stereotype.Repository;

@Repository("selfDao")
public interface SelfDao {
    public Staff selectByAccount(String account);
}
