## 人员管理系统
### 主要技术
- Servlet，jsp
- Spring IOC,Spring AOP
- maBatis
- Mybatis+Spring 整合开发

###  需求
1. 登录界面
2. 个人中心
    - 个人信息
    - 修改密码
3. 人员管理
    - 员工管理：编辑，删除，查看
    - 部门管理：编辑，删除
4. 日志
    - 登录日志
    - 系统日志
    - 操作日志 

### 项目结构
**三层架构**
- 持久层：Mybatis
- 表现层：Servlet+Jsp
- spring：管理对象(IOC)，切面处理(AOP)

**基于MVC模式**
- 视图--JSP
- 模型--JavaBean
- 控制器--Servlet+JavaBean

### 数据库设计
部门：编号，名称，位置
```
create table department{
	id int primary key auto_increment,
	name varchar(20) not null,
	address varchar(20) not null
}
```
日志：操作时间，类型，操作人，模块，操作，结果
```
ceate table  	log {
	opr_time datetime not null,
	type varchar(10) not null,
	operator varchar(20) not null,
	module varchar(20) not null,
	operation varchar(20) not null,
	result varchar(100) not null
}
```
员工：编号，账户，密码，状态，所属部门，姓名，性别，身份证号码，入职时间，离职时间，出生日期，备注
```
create table staff{
	id int primary key auto_increment,
	account varchar(20) not null,
	password varchar(20) not null,
	status varchar(10) not null,
	did int,
	name varchar(20),
	sex char(2),
	id_number char(18),
	work_time datetime,
	leave_time datetime,
	born_date datetime,
	info varchar(200)
}
```
添加外键
```
alter table staff add constraint fk_staff_dep foreign  key(did) references department(id);
```

### 项目环境搭建
> 准备页面原型，配置文件模板
**sm**
- 父module
- 全局定义与组织，不实现具体功能
**sm_service**
- 持久层，业务层
- mybatis依赖，spring依赖
**sm_web**
- 表现层
- servlet依赖

1. 创建普通maven项目名为sm，不实现具体功能，所以删除src
2. 在sm下创建子module：sm_service，普通maven项目
        `数据库`
    - mysql-connector-java
    - mybatis
    ``spring基本包``
    - spring-core   >4
    - spring-beans
    - spring-context
    - spring-aop
    - aspectjweaver
     `事务管理`
    - spring-jdbc
    - spring-tx
    - mybatis-spring
4. 在sm下创建子module：sm_web，maven app
    - sm_service
    - javax.servlet-api
    - jstl

> 设置打包方式 ``<packaging>jar</packaging>``

**service下的包**
- dao
- entity
- service

**web下的包**
- controller
- global

#### 引入配置
**配置spring.xml**
```
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd
    http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop.xsd
    http://www.springframework.org/schema/tx
    http://www.springframework.org/schema/tx/spring-tx.xsd">
	
	
	
</beans>	
	
```
> spring整合mybatis
-  dataSource ：配置数据库(driver,url,username,password)
`class="org.springframework.jdbc.datasource.DriverManagerDataSource"`
-  sqlSessionFactory:可以写在mybatis-config.xml，也可以整合在Spring.xml中
> 声明式事务
- transactionManager
- tx:advice
- aop:config
> 全局扫描
- ``context:component-scan base-packate=""``
- ``aop:aspectj-autoproxy``

#### 工具类
**编码过滤器**
- global下EncodingFilter.java
- 在web.xml中进行配置
```
<filter>
    <filter-name>Encoding</filter-name>
    <filter-class>com.njupt.global.EncodingFilter</filter-class>
    <init-param>
        <param-name>ENCODING</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
</filter>
    <filter-mapping>
        <filter-name>Encoding</filter-name>
        <!--对所有url进行过滤-->
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```

**核心控制器**
global 下DIspatcherServlet
- Servlet对象由Web容器管理，Service对象由IOC容器管理
核心控制器
    - 解析url ，bean的名字，method的名字，交给IOC
配置在web.xml中


dao下  持久层接口
Service下 业务层接口

### 功能实现

#### 部门管理
- 创建实体类Department
- 为Department类创建持久层接口DepartmentDao 并交给Spring管理 (``@Repository("departmentDao")``
   用于操作数据库，一般给出增删改查方法)
- 为Department类创建业务层接口DepartmentService  （我们通过业务层调用的方法，业务层再调用持久层的方法）
- DepartmentServiceImpl实现DepartmentService接口
- 在resources下用xml实现DepartmentDao  :配置resultMap映射， 实现各个方法  
     - xml的路径名通常设置的跟dao的路径名一样，比如dao在com.njupt.dao下，xml就也放在resources的com.njupt.dao下
标签
```
<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.4//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
```
- 部门管理视图层
- 部门管理controller






