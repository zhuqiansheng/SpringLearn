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
List<Map<String,Object>> queryForList(String sql,Object[] args)
List<Map<String,Object>> queryForList(String sql,Object...args)
```


   

   
    