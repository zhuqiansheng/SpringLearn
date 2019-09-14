## SpringMVC

** 核心组件 **

1. DispatcherServlet  :前置控制器，统一调度其他组件的执行
1. Handler :处理器 , 完成具体业务逻辑
1. HandlerMapping : 将请求映射到Handler
1. HandlerInterceptor : 处理器拦截器
1. HandlerExecutionChain ： 处理器执行链
1. HandlerApapter : 处理器适配器
1. ModelAndView : 装载模型数据和视图信息，返回给Servlet
1. ViewResolver ：视图解析器

** 实现流程 **

1. 客户端请求被DispatcherServlet接收
1. DispatcherServlet将请求映射到Handler
1. .生成Handler以及HandlerInterceptor
1. .返回HandlerExecutionChain（Handler\+HandlerInterceptor）
1. .DispatcherServlet通过HandlerAdapter执行Handle
1. 返回一个ModelAndView
1. .DispatcherServlet通过ViewResolver进行解析。
1. 返回填充了模型数据的View，响应给客户端。



![](README\Image.png)
>  开发者只需手动编写Handler，View。

** 步骤** 

1. maven导入spring\-webmvc

 2\. web.xml中配置DispatcherServlet 

```xml
```xml
<servlet>
  <servlet-name>springmvc</servlet-name>
  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  <init-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:springmvc.xml</param-value>
  </init-param>
</servlet>
<servlet-mapping>
  <servlet-name>springmvc</servlet-name>
  <url-pattern>/</url-pattern>
</servlet-mapping>
<!--然后需要编写springmvc.xml-->``````
3\. 编写Handler

```java
```
public class MyHandler implements Controller {
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "张三");
        modelAndView.setViewName("show");
        return modelAndView;
    }
}``````
4\. 在resources下编写配置文件springmvc.xml   \(SpringMVC基础配置\) 

```xml
```
<!--配置handlerMapping，将url请求映射到Handler-->
    <bean id="handlerMapping" class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
        <!--配置mapping-->
        <property name="mappings">
            <props>
                <prop key="/test">testHandler</prop>
            </props>
        </property>
    </bean>
    <bean id="testHandler" class="com.njupt.handler.MyHandler"/>``````
<!--配置视图解析器-->
<!--modelAndView.setViewName("show");   在show的前面加 /  ，在后面加 .jsp-->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/"/>
    <property name="suffix" value=".jsp"/>
</bean>``````
> 当访问 test时 会 出发testHandler指向的 MyHandler ,然后返回  modleAndView 通过视图解析器，转向jsp页面

** 基于注解的方式 **

1. springMVC 基础配置
1. Controller，HandlerMapping 通过注解进行映射
> 在类上方注解 @controller , 在方法上方注解RequestMapping \("/ 虚拟路径"\) ， 注解方式不要求继承Controller
```java
```
@Controller
public class annotationHandler {

    @RequestMapping("/annotationTest")
    public ModelAndView annotationTest() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "李四");
        modelAndView.setViewName("show2");
        return modelAndView;
    }``````
/**
 * Model传值，String进行视图解析
 */
@RequestMapping("/modelTest")
public String ModelTest(Model model) {
    //填充模型数据
    model.addAttribute("name", "jerry");
    //设置逻辑视图
    return "show";
}``````
/**
 * Map传值，String进行视图解析
 */
@RequestMapping("mapTest")
public String MapTest(Map map) {
    //填充数据
    map.put("name", "cat");
    //设置路基视图
    return "show";
}``````1. XML配置ViewResolver组件映射

```
```
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/"/>
    <property name="suffix" value=".jsp"/>
</bean>``````
4\. 用spring将Handler自动扫描到IOC容器中

```xml
```
<context:component-scan base-package="com.njupt.handler"></context:component-scan>``````
___xml方式和注解方式（好像）不能混用___

**编码过滤**

```xml
```
<filter>
  <filter-name>encodingFilter</filter-name>
  <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
  
  <init-param>
    <param-name>encoding</param-name>
    <param-value>UTF-8</param-value>
  </init-param>
  <init-param>
    <param-name>forceEncoding</param-name>
    <param-value>true</param-value>
  </init-param>
</filter>
<filter-mapping>
  <filter-name>encodingFilter</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>``````


表单提交的action = RequestMapping

```java
```xml
action="addGoods" method="post"``````java
@RequestMapping("/addGoods")
public ModelAndView add(Goods goods) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("goods", goods);
    modelAndView.setViewName("cart");
    return modelAndView;
}``````





