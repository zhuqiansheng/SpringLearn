package com.project.school.service;

import com.github.pagehelper.PageInfo;
import com.project.school.common.ServerResponse;
import com.project.school.pojo.User;

public interface UserService {

    ServerResponse<User> login(String username, String password);

    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    ServerResponse<User> updateInformation(User user);

    /**
     * 后台管理员操作
     *
     * @param username
     * @return
     */
    ServerResponse<String> resetStudentPassword(String username);

    ServerResponse<PageInfo> getExerciseHistory(User user, int pageNum, int pageSize);
}
