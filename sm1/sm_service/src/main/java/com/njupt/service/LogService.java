package com.njupt.service;

import com.njupt.entity.Log;

import java.util.List;

public interface LogService {

    //添加系统日志
    void addSystemLog(Log log);

    //添加登录日志
    void addLoginLog(Log log);

    //添加操作日志
    void addOperationLog(Log log);

    List<Log> getSystemLog();

    List<Log> getLoginLog();

    List<Log> getOperationLog();
}
