package com.njupt.service;

import com.njupt.entity.Staff;

public interface SelfService {

    Staff login(String account, String password);

    void changePassword(Integer id, String password);
}
