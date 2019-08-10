### 什么是AOP
>AOP：面向切面编程，采取横向抽取机制，取代了传统纵向继承体系重复性代码（性能监视，事务管理，安全检查，缓存）,使用纯Java代码实现，不需要专门的编译过程和类加载器，在运行期通过代理方式向目标类织入增强代码

### AOP相关术语
- Joinpoint：连接点，指能被连接到的点，这些点是方法
- Pointcut：切入点，实际进行拦截的Joinpoint
- Advice：增强，拦截到Joinpoint后所要做的事
- Target：代理的目标对象
- Weaving：织入，把增强应用到目标对象来创建新的代理对象的过程
- Proxy：代理，一个类被AOP织入增强后，就产生一个结果代理类
- Aspect: 切面，是切入点和通知的结合

### AOP的底层实现原理
1. 若目标对象实现了接口，spring使用jdk的动态代理
2. CGLIB：若目标对象没有实现任何接口，spring使用CGLIB库生成目标对象的子类

   **jdk动态代理，是针对接口生成子类，接口中方法不能使用final修饰**
   **CGLIB是针对目标类生产子类，因此类或方法不能使final修饰**
   
   
### Spring AOP增强类型
按照通知Advice在目标类方法的连接点位置，可分为
1. MethodBeforeAdvice：前置通知，在目标方法执行前实施增强
2. AfterReturningAdvice:后置通知，在目标方法执行后实施增强
3. MethodIntercepter：环绕通知，在目标方法执行前后都实施增强
4. ThrowsAdvice：异常抛出通知，在方法抛出异常后实施增强

### Spring AOP切面类型
1. Advisor：一般切面，对目标类所有方法进行拦截
2. PointcutAdvisor：代表具有切点的切面，可以指定拦截目标类哪些方法

#### 配置
- 首先Maven导入aopalliance和spring-aop
- 自己的前置增强类实现MethodBeforeAdvice接口
- ```@ContextConfiguration("classpath:applicationContext.xml")```

#### 在xml中配置代理类的属性设置：
- target：目标类
- proxyInterfaces：实现的接口;如果有多个接口，用```list <value></value>
  </list>```
- interceptorNames：拦截的名称

其他
- proxyTargetClass：true时，使用CGLib代理
- interceptorNames：需要织入目标的Advice
- singleton：返回代理是否为单例
- optimize：true时，强制使用CGLib

