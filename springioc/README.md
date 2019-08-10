#### 配置
1. 写pom文件
```
//spring所需
spring-core
spring-context
spring-beans
spring-expression
//日志
commons-logging
log4j
//测试
junit
```
2. 在main下创建Java目录，标记为源码文件
3. 在resources下创建applicationContext文件

#### spring ioc
>  com.njupt.ioc.demo1
- spring是一个大工厂，可以将所有对象创建和依赖关系维护，交给Spring管理 
```
//使用spring的工厂
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//通过工厂获得类
UserService  userService =(UserService)applicationContext.getBean("UserService");
userService.sayHello(); 
```
- IOC:Inverse of Control
  反转控制的改建，就是将原本在程序中手动创建UserService对象的控制权 
  交由Spring框架
  
#### bean的三种实例化方式
>  com.njupt.ioc.demo2
1. 无参构造器方式 ：使用最多 
2. 静态工厂方式
3. 实例工厂方式

#### Bean的配置
- id作为Bean的名称，在IOC容器中必须是唯一的
- 如果带有特殊字符，要改用6name，其他时候都用id
- class用于设置一个类的完全路径名称

#### Bean的作用域
- scope=singleton(默认)：单例 
- scope=prototype：每次调用getBean()时都会返回一个新的实例，多例
- 在bean的配置里可用init-method和destroy-method设定初始化和销毁时调用的函数
 
#### spring的属性注入
1. 构造函数注入: ```<contructor-org name="" value=""/>```
2. 属性setter方法注入: ```<property name="" value=""/>```
   如果是对象```<property name="" ref=""/>```ref指向对象的bean id
   
- 使用命名空间方式: 创建命名空间p  
  ```xmlns:p="http://www.springframework.org/schema/p"```
  
  普通属性 p:name    
  对象属性 p:name-ref 
-  spel方式：#{} ;适合比较复杂的情况 

- 复杂类型的属性注入：Array，set，list，Map，properties：```<property
  name="">```
  
### 注解方式
1. 引入额外的依赖：spring-aop
2. 引入约束:
```
xmlns:context="http://www.springframework.org/schema/context"
xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context 
        http://www.springframework.org/schema/context/spring-context.xsd">
```
3. 在类的上方注解 :```@Component("id")```
4. 配置文件中开启注解扫描 ```<context:component-scan base-package="包名"/>```

#### 多种注解
1. @Componment
2. @Repository ：用于对DAO实现类进行标注
3. @Service ：用于对Service实现类进行标注
4. @Controller ：用于对Controller实现类进行标注

