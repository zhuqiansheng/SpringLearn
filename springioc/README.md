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
[传统方式和spring方式的对比](src/main/java/com/njupt/ioc/demo1/SpringDemo1.java)
- spring是一个大工厂，可以将所有对象创建和依赖关系维护，交给Spring管理 
```
//使用spring的工厂
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//通过工厂获得类
UserService  userService =(UserService)applicationContext.getBean("UserService");
userService.sayHello(); 
```
- IOC:Inverse of Control
  反转控制的概念，就是将原本在程序中手动创建UserService对象的控制权 
  交由Spring框架，也就是创建UserService对象控制权被反转到了Spring框架
  
  
#### Bean的配置
- id作为Bean的名称，在IOC容器中必须是唯一的
- 如果带有特殊字符，要改用name，其他时候都用id
- class用于设置一个类的完全路径名称


#### bean的三种实例化方式
  [demo](src/main/java/com/njupt/ioc/demo2)
1. 无参构造器方式 ：使用最多 
```
//Bean1只有无参构造函数
<bean id="Bean1" class="com.njupt.ioc.demo2.Bean1"/>

```
2. 静态工厂方式
```
//Bean2没有无参构造函数，Bean2Factory有static方法createBean2，返回Bean2的实例对象
<bean id="Bean2" class="com.njupt.ioc.demo2.Bean2Factory" factory-method="createBean2"></bean>
```
3. 实例工厂方式
```
//Bean3没有无参构造函数,Bean3Factory有普通方法createBean3,返回Bean3的实例对象
    <bean id="bean3Factory" class="com.njupt.ioc.demo2.Bean3Factory"></bean>
    <bean id="Bean3" factory-bean="bean3Factory" factory-method="createBean3"></bean>
```

#### Bean的作用域
[demo](src/main/java/com/njupt/ioc/demo3)
- scope=singleton(默认)：单例 
- scope=prototype：每次调用getBean()时都会返回一个新的实例，多例
- 在bean的配置里可用init-method和destroy-method设定初始化和销毁时调用的函数

#### Spring容器中Bean的生命周期

1. instantiate bean 对象实例化
2. populate properties 封装属性
3. 如果Bean实现BeanNameAware 执行setBeanName
4. 如果Bean 实现BeanFactoryAware 或者ApplicationContextAware设置工厂 setBeanFactory或者上下文对象setApplicationContext
5. 如果存在类实现BeanPostProcessor(后处理Bean)，执行postProcessBeforeInitialization
6. 如果Bean实现InitializingBean执行afterPropertiesSet
7. 调用`<bean init-method="init"> `指定初始化方法init如果存在类实现BeanPostProcessor(处理Bean),
    执行postProcessAfterInitialization
8. 执行业务处理
9. 如果Bean实现DisposableBean执行destroy
10. 调用`<bean destroy-method="customerDestroy">` 指定销毁方法customerDestroy
 
#### spring的属性注入
[demo](src/main/java/com/njupt/ioc/demo4)
1. 构造函数注入: ```<contructor-org name="" value=""/>```
2. 属性setter方法注入: ```<property name="" value=""/>```
   如果是对象```<property name="" ref=""/>```ref指向对象的bean id
   
- 使用命名空间方式: 创建命名空间p  
  普通属性 p:name    
  对象属性 p:name-ref 
  ```
  xmlns:p="http://www.springframework.org/schema/p"
  //效果与setter方法注入相同
  <bean id="person" class="com.njupt.ioc.demo4.Person" p:name="大黄" p:age="18" p:cat-ref="cat"></bean>
  ```

-  spel方式：#{} ;适合比较复杂的情况,可调用函数，可计算

- 复杂类型的属性注入：[demo](src/main/resources/applicationContext.xml)

         `Array，set，list，Map，properties：`


    
  
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
[demo](src/main/java/com/njupt/ioc/demo6/UserDao.java)
1. @Componment
2. @Repository ：用于对DAO实现类进行标注
3. @Service ：用于对Service实现类进行标注
4. @Controller ：用于对Controller实现类进行标注 

#### Spring的属性注入--注解方式
[demo](src/main/java/com/njupt/ioc/demo6/UsersService.java)
-  普通属性:若有setter方法，在方法上方@Value("value");若没有setter方法，在属性定义上方@Value("value")。
-  对象属性： 
1. @Autowired 默认按照类型进行注入，required属性，设置一定要找到匹配的Bean
2. @AutoWired+@Qualifier指定注入Bean 的名称
3. @Qualifier(name="") 指定Bean名称

#### bean生命周期方法的注解
[demo](src/main/java/com/njupt/ioc/demo7/Bean1.java)
- 在初始化方法上注解@PostConstruct，该方法将在对象被创建后执行
- 在销毁方法上注解@PreDestroy,该方法将在工厂被关闭时执行
- @Scope注解用于指定Bean作用返回，默认为singleton单例模式，可改为prototype多例模式

#### xml配置和注解配置混合使用
- 引入context命名空间,在配置文件中添加标签：`<context:annotation-config/>`
- 类用xml配置，属性注入用注解配置

