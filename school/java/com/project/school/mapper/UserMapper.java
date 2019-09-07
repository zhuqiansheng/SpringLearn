package com.project.school.mapper;

import com.project.school.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(String username);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username);

    User selectLogin(@Param("username") String username, @Param("password") String password);

    int checkPassword(@Param("password") String password, @Param("username") String username);

    int checkEmailByUsername(@Param("email") String email, @Param("username") String username);

    int checkUserRole(String username);

    int resetStudentPassword(@Param("username") String username, @Param("password") String password);

}