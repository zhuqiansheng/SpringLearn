package com.project.school.mapper;

import com.project.school.pojo.ShapeCode;

public interface ShapeCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShapeCode record);

    int insertSelective(ShapeCode record);

    ShapeCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShapeCode record);

    int updateByPrimaryKey(ShapeCode record);
}