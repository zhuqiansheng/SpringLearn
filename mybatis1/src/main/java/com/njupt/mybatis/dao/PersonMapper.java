package com.njupt.mybatis.dao;

import com.njupt.mybatis.bean.Person;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface PersonMapper {
    public void deletePerson(Integer id);

    public Person getPersonByNameAndGender1(String username, String gender);

//    封装POJO类
    public Person getPersonByNameAndGender2(Person person);

//    map
    public Person getPersonByNameAndGender3(Map<String,Object> map);

//    param方式，第一种方式默认设置@Param("param1"),@Param("param2")......
    public Person getPersonByNameAndGender4(@Param("username") String username,@Param("gender") String gender);

    public Person getPersonByCollection1(Collection list);
    public Person getPersonByCollection2(int[] ids);
//    public Person getPersonByCollection2(@Param("test") int[] ids);

    //返回集合
    public List<Person> getPersonsByIds(int[] ids);



}