package com.project.school.mapper;

import com.project.school.pojo.CommonCncInfo;

public interface CommonCncInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonCncInfo record);

    int insertSelective(CommonCncInfo record);

    CommonCncInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonCncInfo record);

    int updateByPrimaryKey(CommonCncInfo record);
}