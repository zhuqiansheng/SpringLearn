### myBatis参数传递

#### 项目构建
1. pom:引入mybatis，mysql，junit
2. 创建包bean，dao
   - bean下面放实体类
   - dao下放接口PersonMapper
3. 编写resources文件,用xml实现dao下的接口，(PersonMapper.xml实现PersonMapper.java)

#### 单参数
PersonMapper中方法`public void deletePerson(Integer id);`，传递一个参数，mybatis可以自动识别
```
    <!--单参数,id=方法名-->
    <!--根据id删除person数据-->
    <delete id="deletePerson" parameterType="int">
        delete from person where id=#{id}    //{}内可以随意
    </delete>
```

#### 多参数
[PersonMapper.xml](src/main/resources/mybatis/PersonMapper.xml)

[parameterTest](src/test/java/parameterTest.java)

- 有JavaBean的情况下可以封装为POJO
- 没有Bean的情况下可以封装为Map
- 使用@Param 注解方式

总结：
- 使用map传递参数，业务可读性差
- 使用@Param，受到参数个数的影响，建议n<5时使用，否则封装为POJO

#### 集合类型参数处理
- 当参数为Collection接口，转换为Map，Map的key为collection
- 如果参数类型为List接口，除了collection 的值外，list作为key
- 如果参数为数组，也会转换为Map，map的key为array,即`select * from person where id=#{array[0]}`,也可以使用@param,这样就将array替换为param名

#### foreach
- 用于sql中 select * from tb where ...  查找集多个，返回集也是多个


