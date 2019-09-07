package com.project.school.mapper;

import com.project.school.pojo.CncStruct;

public interface CncStructMapper {
    int deleteByPrimaryKey(String code);

    int insert(CncStruct record);

    int insertSelective(CncStruct record);

    CncStruct selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(CncStruct record);

    int updateByPrimaryKey(CncStruct record);
}