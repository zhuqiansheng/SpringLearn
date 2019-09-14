package com.njupt.dao;

import com.njupt.entity.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("logDao")
public interface LogDao {
    void insert(Log log);

    List<Log> selectByType(String Type);
}
