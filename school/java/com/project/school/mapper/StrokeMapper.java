package com.project.school.mapper;

import com.project.school.pojo.Stroke;

public interface StrokeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Stroke record);

    int insertSelective(Stroke record);

    Stroke selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Stroke record);

    int updateByPrimaryKey(Stroke record);
}