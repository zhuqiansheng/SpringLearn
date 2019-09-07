package com.project.school.mapper;

import com.project.school.pojo.QuestionArea;

public interface QuestionAreaMapper {
    int deleteByPrimaryKey(Integer areaId);

    int insert(QuestionArea record);

    int insertSelective(QuestionArea record);

    QuestionArea selectByPrimaryKey(Integer areaId);

    int updateByPrimaryKeySelective(QuestionArea record);

    int updateByPrimaryKey(QuestionArea record);



}