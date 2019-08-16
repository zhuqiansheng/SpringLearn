## JDBC Template

### 简介
> 为了简化持久化操作，Spring在JDBC API之上提供了JdbcTemplate组件

- 没有JdbcTemplate：代码—>JDBC—>JDBC驱动—>数据库
- 有了JdbcTemplate：代码—>JDBCTemplate—>JDBC API—>JBDC驱动—>数据库

###  环境搭建
1. 创建maven项目
2. 编写pom.xml，导入
   - spring组件(core,context,beans,aop)
   - jDBC Template(spring-jdbc,spring-tx)  
   - Mysql驱动(mysql下的mysql-connector-java)
3. 创建数据库
4. Spring配置
   - 引入命名空间
   - 配置数据库连接池信息,url,username,password...: 
   - 配置Template
   
   
### JDBC Template基本使用
- execute方法
- `update与batchUpdate方法`:执行同构sql语句时效率高
- `query与queryXXX方法`
- call方法

#### update和batchUpdate
[updateTest](src/test/java/updateTest.java)
```
int update(String sql,Object[] args)
int update(String sql,Object... args)       //新出来的方法，推荐使用
int[] batchUpdate(String[] sqls)
int[] batchUpdate(String sql,List<Object[]> args)  //可以使用？占位符
```
#### query与queryXXX
- 查询简单数据项
```
//获取一个
T queryForObject(String sql,Class<T> type)
T quertForObject(String sql,Object[] args,Class<T> type)
T quertForObject(String sql,Class<T> type,Object...)  //可变参数必须定义在最后
//获取多个
List<T> queryForList(String sql,Class<T> type)   //不可使用占位符
List<T> queryForList(String sql,Object[] args,Class<T> type) 
List<T> queryForList(String sql,Class<T> type,Object...)  //可变参数
```
- 查询复杂对象（封装为Map）
[queryTest](src/test/java/queryTest.java)
```
//获取一个
Map queryForMap(String sql)
Map queryForMap(String sql,Object[] args)
Map queryForMap(String sql,Object...args)
//获取多个
List<Map<String,Object>> queryForList(String sql)
...同上
```
- 查询复杂对象（封装为实体对象）
1. 首先创建一个类，对应于表结构
2. 实现RowMapper接口(用private)，将表与类映射起来，如果只用到一次可以设计成内部类
```
//获取一个
T queryForObject(String sql,RowMapper<T> mapper)
T queryForObject(String sql,Object[] args,RowMapper<T> mapper)
T queryForObject(String sql,RowMapper<T> mapper,Object...)
//获取多个
List<T> query()  
...同上
```

#### 总结
- 查询简单数据项需要给出返回值的类型,查询复杂数据项不需要给出返回值类型，因为会进行封装
- 查询多个，除了封装成实体对象用query，其它都是queryForList
- 更新操作推荐使用可变参数形式

### JDBC Template持久层 
[Dao](src/main/java/com/njupt/sc/dao/Impl)

在com.njupt.sc包下
- 创建包entity：用于存放类的定义
- 创建包dao ：定义访问关系型数据库系统所需操作的接口
- 在dao下创建包Impl：用于实现接口

### 优缺点分析
- 优点：相对于jdbc简单，灵活
- 缺点：SQL语句与Java代码掺杂，功能不丰富，没有很好的解决ORM(对象和关系的映射)


   

   
    