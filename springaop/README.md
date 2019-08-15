### 什么是AOP
>AOP：Aspect Oriented Programing,面向切面编程，采取横向抽取机制，取代了传统纵向继承体系重复性代码（性能监视，事务管理，安全检查，缓存）,使用纯Java代码实现，不需要专门的编译过程和类加载器，在运行期通过代理方式向目标类织入增强代码

### AOP相关术语
- Joinpoint：连接点，指能被拦截到的点，这些点是方法
- Pointcut：切入点，实际进行拦截的Joinpoint
- Advice：增强，拦截到Joinpoint后所要做的事
- Target：代理的目标对象
- Weaving：织入，把增强应用到目标对象来创建新的代理对象的过程
- Proxy：代理，一个类被AOP织入增强后，就产生一个结果代理类
- Aspect: 切面，是切入点和通知的结合

### AOP的底层实现原理
1. 若目标对象实现了接口，spring使用jdk的动态代理  [MyjdkProxy](src/main/java/com/njupt/aop/demo1/MyjdkProxy.java)
2. CGLIB：若目标对象没有实现任何接口，spring使用CGLIB库生成目标对象的子类  [MyCglibProxy](src/main/java/com/njupt/aop/demo2/MyCglibProxy.java)
   
> CGlib采用非常底层字节码技术，可以为一个类创建子类，解决无接口代理问题*
   
     jdk动态代理，是针对接口生成子类，接口中方法不能使用final修饰
     CGLIB是针对目标类生产子类，因此类或方法不能使final修饰
   
   
### Spring AOP增强类型
按照通知Advice在目标类方法的连接点位置，可分为
1. MethodBeforeAdvice：前置通知，在目标方法执行前实施增强    [前置通知demo](src/main/java/com/njupt/aop/demo3)
2. AfterReturningAdvice:后置通知，在目标方法执行后实施增强
3. MethodIntercepter：环绕通知，在目标方法执行前后都实施增强
4. ThrowsAdvice：异常抛出通知，在方法抛出异常后实施增强


#### 配置
- 首先Maven导入aopalliance和spring-aop
- 自己的前置增强类实现MethodBeforeAdvice接口
- ```@ContextConfiguration("classpath:applicationContext.xml")```


### Spring AOP切面类型
1. Advisor：一般切面，对目标类**所有方法**进行拦截
   [Advisor_demo](src/main/resources/applicationContext.xml)
2. PointcutAdvisor：代表具有切点的切面，可以指定拦截目标类哪些方法
   [PointcutAdvisor_demo](src/main/resources/applicationContext2.xml)


#### 在xml中配置代理类的属性设置：
```class="org.springframework.aop.framework.ProxyFactoryBean"```
- target：目标类
- proxyInterfaces：实现的接口（如果有）;若实现多个接口，用```list <value></value>
  </list>```
- interceptorNames：拦截的名称(需要织入目标的Advice)

**其他**
- proxyTargetClass：true时，使用CGLib代理，没有实现接口时，用它来代替proxyInterfaces
- singleton：返回代理是否为单例
- optimize：true时，强制使用CGLib

#### 配置带有切入点的切面
针对某些方法进行增强，使用jdkRegexpMethodPointcut构造正则表达式切点 

需配置
```    
<bean id="myAdvisor" class="org.springframework.aop.support.RegexpMethodPointcutAdvisor">
        <property name="patterns" value=".*save.*,.*delete.*"/>
        <property name="advice" ref="myAroundAdvice"/>
</bean>
```
**这种方式会比较麻烦，下面采用自动代理**

#### 基于Bean名称的自动代理方式  BeanNameAutoProxyCreator
[配置](src/main/resources/applicationContext3.xml) 为Bean名称以Dao结尾的配置自动代理
```
    <bean class="org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator">
        <property name="beanNames" value="*Dao"/>
        <property name="interceptorNames" value="myBeforeAdvice"/>
    </bean>
```
这种方式是对所有方法产生代理，不能只对某些方法代理

#### 基于切面信息的自动代理
```
    <!--配置切面-->
    <bean id="myAdvisor" class="org.springframework.aop.support.RegexpMethodPointcutAdvisor">
        <property name="pattern" value=".*save.*,.*delete.*"/>
        <property name="advice" ref="myAroundAdvice"/>
    </bean>

    <bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"></bean>
```




