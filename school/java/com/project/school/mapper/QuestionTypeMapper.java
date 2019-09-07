package com.project.school.mapper;

import com.project.school.pojo.QuestionType;

public interface QuestionTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(QuestionType record);

    int insertSelective(QuestionType record);

    QuestionType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(QuestionType record);

    int updateByPrimaryKey(QuestionType record);
}