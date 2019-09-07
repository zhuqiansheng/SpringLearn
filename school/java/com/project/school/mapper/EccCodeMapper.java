package com.project.school.mapper;

import com.project.school.pojo.EccCode;

public interface EccCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EccCode record);

    int insertSelective(EccCode record);

    EccCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EccCode record);

    int updateByPrimaryKey(EccCode record);
}