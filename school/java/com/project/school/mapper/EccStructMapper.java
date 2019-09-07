package com.project.school.mapper;

import com.project.school.pojo.EccStruct;

public interface EccStructMapper {
    int deleteByPrimaryKey(Integer code);

    int insert(EccStruct record);

    int insertSelective(EccStruct record);

    EccStruct selectByPrimaryKey(Integer code);

    int updateByPrimaryKeySelective(EccStruct record);

    int updateByPrimaryKey(EccStruct record);
}