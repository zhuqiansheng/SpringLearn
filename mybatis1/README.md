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

#### 批量插入
**传统jdbc批量插入**

[Bath](src/test/java/BathTest1.java)
for循环方式需要频繁获取Session，获取连接，效率低，而batch方式也未解决sql代码与java代码参杂的问题

**使用Mybatis支持批量插入的配置和语法**

sql中插入多条数据有两种写法
```
//第一种方式，连写，逗号分隔
insert tb(var1,var2...)  values(v1,v2...),(v1,v2..),..
//第二种方式，分开写，分号分隔
insert tb(var1,var2...)  values(v1,v2...);
insert tb(var1,var2...)  values(v1,v2...);
```
在mybatis中也对应于两种不同方式

第一种:foreach标签
```
    //传入的是一个list
    <!--批量添加-->
    <insert id="addPersons">
        insert into person(username, email, gender) values
        <foreach collection="persons" item="person" separator=",">
            (#{person.username},#{person.email},#{person.gender})
        </foreach>
    </insert>
```
第二种:借助数据库连接属性allowMultiQueries=true
> 需要在url后面添加特性allowMultiQueries=true
```
    <!--批量添加-->
    <insert id="addPersons">
       <foreach collection="persons" item="person" separator=";">
             insert into person(username, email, gender) values   
            (#{person.username},#{person.email},#{person.gender})
        </foreach>
    </insert>
```

- 这两种方式都是字符串拼接的方式，在数据量很大的时候效率不高
- 可借助Executor的Batch批量添加，它可与Spring框架整合，实际开发中通常使用这种方式
- 这种方式需要使SQLSession获得批处理的能力`        SqlSession sqlSession = getSqlSessionFactory().openSession(ExecutorType.BATCH); `

```
//Mapper
    <insert id="addPerson" parameterType="Person">
        insert into person(username, email, gender) values(#{username},#{email},#{gender})
    </insert>
//注意点：paramterType写了Person之后，就不要再使用person.username这种方式


//Test
    public void testBatchForExecutor() {
        //使sqlSession获得批处理的能力
        SqlSession sqlSession = getSqlSessionFactory().openSession(ExecutorType.BATCH);
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

        for (int i = 0; i < 10000; i++) {
            personMapper.addPerson(new Person("tom" + i, "tom@qq.com", "F"));
        }
        sqlSession.commit();
        sqlSession.close();
    }
```

#### 拦截器
编写一个拦截器
[MyFirstInteceptor](src/main/java/com/njupt/mybatis/interceptpor/MyFirstInteceptor.java)

在mybatis-config.xml中添加插件配置
```
    <plugins>
        <plugin interceptor="com.njupt.mybatis.interceptpor.MyFirstInteceptor">
            <property name="hello" value="world"/>
        </plugin>
    </plugins>
```
则在调用一个返回结果集合的方法时就会对他进行拦截，做一些增强操作
```
插件配置的初始化参数{hello=world}
将要包装的目标对象 org.apache.ibatis.executor.CachingExecutor@5ce81285
将要包装的目标对象 org.apache.ibatis.scripting.defaults.DefaultParameterHandler@51cdd8a
将要包装的目标对象 org.apache.ibatis.executor.resultset.DefaultResultSetHandler@4e7dc304
将要包装的目标对象 org.apache.ibatis.executor.statement.RoutingStatementHandler@10bbd20a
拦截的目标对象  org.apache.ibatis.executor.resultset.DefaultResultSetHandler@4e7dc304
[]
```

**开发过程**
1. 确定拦截的签名：@Intercepts({@Signature(),@Signature(),...}  )
2. 实现拦截方法：intercept
3. 配置和运行  :在mybatis的xml配置文件中

**多插件开发过程**
1. 创建代理对象时，按照插件配置的顺序进行包装
2. 执行目标方法时，是按照代理的逆向进行执行
> 为一个对象创建多个代理，相当于一层一层进行包装，而当执行时，是由外而内，一层一层的执行

#### 使用拦截器PageHelper进行分页
**分页的分类**
1. 内存分页：(缺)内存开销大，(优)减少与db的交互
2. 物理分页：相反
> mysql中实现分页用limit

**pageHelper帮助文档**

[pageHelper](https://github.com/pagehelper/Mybatis-PageHelper)








