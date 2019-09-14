package com.njupt.service.Impl;

import com.njupt.dao.LogDao;
import com.njupt.entity.Log;
import com.njupt.service.LogService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("logService")
public class LogServiceImpl implements LogService {

    private LogDao logDao;
    public void addSystemLog(Log log) {
        log.setOprTime(new Date());
        log.setType("System");
        logDao.insert(log);
    }

    public void addLoginLog(Log log) {
        log.setOprTime(new Date());
        log.setType("Login");
        logDao.insert(log);
    }

    public void addOperationLog(Log log) {
        log.setOprTime(new Date());
        log.setType("Operation");
        logDao.insert(log);
    }

    public List<Log> getSystemLog() {
        return logDao.selectByType("System");
    }

    public List<Log> getLoginLog() {
        return logDao.selectByType("Login");
    }

    public List<Log> getOperationLog() {
        return logDao.selectByType("Operation");
    }
}
