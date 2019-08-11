## Spring的基于AspectJ的AOP开发

### AspectJ
> 是一个基于Java语言的AOP框架，简化了AOP，之后的开发都建议使用AspectJ方式来开发AOP

### 环境搭建
1. 创建Maven项目于，src\main 下新建java目录，标记为源码文件
2. 编写pom文件，导入额外包：
```
spring-aop 
aopalliance  
spring-aspects  
aspectj.weaver
```
3. 编写spring配置文件：在resources下新建spring的".xml"配置文件,添加额外约束
```
xmlns:aop="http://www.springframework.org/schema/aop"
http://www.springframework.org/schema/aop 
http://www.springframework.org/schema/aop/spring-aop.xsd">
```
4. 开启AspectJ的注解开发，自动代理
```
<aop:aspectj-autoproxy/>
```

#### AspectJ提供不同的通知类型
- @Before :前置通知，相当于BeforeAdvice
- @AfterRuntuning :后置通知，相当于AfterReturningAdvice
- @Around ：环绕通知，相当于MethodInterceptor
- @AfterThrowing ：异常抛出通知，相当于ThrowAdvice
- @After ：最终final通知，不管是否异常，该通知都会执行

#### 在通知中通过value属性定义切点
 **通过execution函数**

语法：`execution(<访问修饰符>?<返回类型><方法名>(<参数>)<异常>)`

*这与定义方法时的顺序是一样的，访问修饰符指的是public...可省略*

例如：
`
- 匹配所有类的public方法 ：execution(public * *(..))
- 匹配指定包下所有类的所有方法 ：execution(* com.njupt.bao.*(..))
- executin(* com.njupt.bao..*(..))  ..*表示包，子包下所有类
- 匹配实现特定接口所有类方法：executionn(* com.njupt.dao.GenericDAO+.*(..))  //GenericDAO是一个接口
- 匹配所有save开头的方法:execution(*save*(..))
`

**定义切面类**

- 在类上方注解@Aspect
- 在方法上方注解通知类型，通知类型内写execution函数
- 在xml中配置这个切面类

### 通知
**前置通知**
```

    @Before(value="execution(* com.njupt.aspectj.demo1.ProductDao.save(..))")
    //    com.njupt.aspectj.demo1包下ProductDao类的所有类型的所有方法
    //可传入JoinPoing获得通知信息，（非必须）
    public void mybefore(JoinPoint joinPoint){
        System.out.println("前置通知。。"+joinPoint);
    }
```
**后置通知**
```
    //有返回值的，可以在通知里获得这个返回值
    @AfterReturning(value = "execution(* com.njupt.aspectj.demo1.ProductDao.delete(..)))",returning = "returning")
    public void afterReturning(Object returning){
        System.out.println("后置通知。。。"+returning);
    }
```
**环绕通知**

*有返回值，返回值就是目标函数的返回值*

*如果不调用 ProceedingJoinPoint的 proceed方法，那么目标方法就被拦截了*
```
    @Around(value="execution(* com.njupt.aspectj.demo1.ProductDao.update(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕前通知。。。");
        Object obj=joinPoint.proceed();  //执行目标方法
        System.out.println("环绕后通知");
        return obj;
    }
```
**异常通知**
```
    @AfterThrowing(value ="execution(* com.njupt.aspectj.demo1.ProductDao.findOne(..))",throwing = "e")
    public void afterThrowing(Throwable e){
        System.out.println("异常抛出通知。。。"+e.getMessage());
    }  //可以没有throwing
```
**最终通知**   与finally类似

#### 通过@Pointcut为切点命名
- 在每个通知内定义切点，会造成工作量大，不易维护，对于重复的切 点，可以使用@Pointcut进行定义
- 切点方法：private void 无参数方法，方法名为切点名  
- 当通知多个切点时，可以使用|| 进行连接 


