## SpringMVC

**核心组件**

1. DispatcherServlet  :前置控制器，统一调度其他组件的执行
1. Handler :处理器 , 完成具体业务逻辑
1. HandlerMapping : 将请求映射到Handler
1. HandlerInterceptor : 处理器拦截器
1. HandlerExecutionChain ： 处理器执行链
1. HandlerApapter : 处理器适配器
1. ModelAndView : 装载模型数据和视图信息，返回给Servlet
1. ViewResolver ：视图解析器

**实现流程**

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

**步骤** 

1. maven导入spring-webmvc

2. web.xml中配置DispatcherServlet 


```
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
<!--然后需要编写springmvc.xml-->
```
3. 编写Handler

```
public class MyHandler implements Controller {
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "张三");
        modelAndView.setViewName("show");
        return modelAndView;
    }
}
```
4. 在resources下编写配置文件springmvc.xml (SpringMVC基础配置) 

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
    <bean id="testHandler" class="com.njupt.handler.MyHandler"/>
 ```
 ```
<!--配置视图解析器-->
<!--modelAndView.setViewName("show");   
<!--在show的前面加 /  ，在后面加 .jsp-->-->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/"/>
    <property name="suffix" value=".jsp"/>
</bean>
```

> 当访问 test时 会 出发testHandler指向的 MyHandler ,然后返回  modleAndView 通过视图解析器，转向jsp页面

**基于注解的方式**

1. springMVC 基础配置
1. Controller，HandlerMapping 通过注解进行映射
> 在类上方注解 @controller , 在方法上方注解RequestMapping("/ 虚拟路径") ， 注解方式不要求继承Controller
```
@Controller
public class annotationHandler {

    @RequestMapping("/annotationTest")
    public ModelAndView annotationTest() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "李四");
        modelAndView.setViewName("show2");
        return modelAndView;
    }
```
```
/**
 * Model传值，String进行视图解析
 */
@RequestMapping("/modelTest")
public String ModelTest(Model model) {
    //填充模型数据
    model.addAttribute("name", "jerry");
    //设置逻辑视图
    return "show";
}
```
```
/**
 * Map传值，String进行视图解析
 */
@RequestMapping("mapTest")
public String MapTest(Map map) {
    //填充数据
    map.put("name", "cat");
    //设置路基视图
    return "show";
}
```
1. XML配置ViewResolver组件映射


```
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/"/>
    <property name="suffix" value=".jsp"/>
</bean>
```
4. 用spring将Handler自动扫描到IOC容器中

`
<context:component-scan base-package="com.njupt.handler"></context:component-scan
`

___xml方式和注解方式（好像）不能混用___

**编码过滤**

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
</filter-mapping>
```


表单提交的action = RequestMapping

```
action="addGoods" method="post"
```
```
java
@RequestMapping("/addGoods")
public ModelAndView add(Goods goods) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("goods", goods);
    modelAndView.setViewName("cart");
    return modelAndView;
}
```

## RESTful风格
1. RESTful 不是一套标准，只是一种开发方式，架构思想
2. 它时url更加简洁，有利于不同系统之间的资源共享

RESTful就是HTTP协议的四种形式的基本操作
1. GET 获取资源
2. POST 新建资源
3. PUT 修改资源
4. DELETE 删除资源

先 隐藏 Http请求的方法 (post,get,delete,put)
```
  <filter>
    <filter-name>hiddenHttpMethodFilter</filter-name>
    <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>hiddenHttpMethodFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
```

对于一个请求，`action=${pageContext.request.contextPath}/add method=post`

在Controller中 用注解方式配置一个Mapping接收请求 `PostMapping(value ="add")`

[CourseController](src/main/java/com/njupt/Controller/CourseController.java)

get请求 : `GetMapping`
put请求 : 在method里写post,在提交上方添加隐藏域,将post请求转换成put请求
```
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-3">
                <input type="hidden" name="_method" value="PUT"/>
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
```
delete请求:在method里写post，在提交上方添加隐藏域,将post请求转换成delete请求
```
 <form action="${pageContext.request.contextPath}/delete/${course.id}" method="post">
                                <button class="btn btn-danger btn-sm delete_btn" type="submit">
                                    <input type="hidden" name="_method" value="DELETE"/>
                                    <span class="glyphicon glyphicon-trash">删除</span>
                                </button>
                            </form>
```

## SpringMVC数据绑定
将HTTP请求中中的参数绑定到Handler业务方法的形参上

HandlerAdapter-->HttpMessageConverter--(DataBind)-->Handler

> 传统方式中页面传过来的数据都要先通过getParameter()方法获取，然后转换成需要的类型（如id需要将它转换成int类型）
> 如果需要封装成对象，也需要手动封装，SpringMVC数据绑定可以自动实现封装，只要name=属性名

**常用的数据绑定类型**

[DataBindController](src/main/java/com/njupt/Controller/DataBindController.java)

[jsp页面](src/main/webapp)
1. 基本数据类型
```
    /**
     * 基本类型
     * http://localhost:8080/baseType?id=2
     * 将url里的参数 id 传给形参里的id
     */
    @RequestMapping(value = "/baseType")
    //ResponseBody的意思是直接返回到客户端，不跳转到其他页面
    @ResponseBody
    public String baseType(@RequestParam(value = "id") int id) {
        return "id:" + id;
    }
```
2. 包装类
```
    /**
     * http://localhost:8080/packageType?text=zzzzz
     * 包装类型
     */
    @RequestMapping(value= "/packageType")
    @ResponseBody
    public String packageType(@RequestParam(value = "text") String text) {
        return "text:" + text;
    }
```
3. 数组
```
/**
     *http://localhost:8080/arrayType?name=zhangsan&name=lisi
     */
    @RequestMapping(value="arrayType")
    @ResponseBody
    public String arrayType(String[] name) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String item : name) {
            stringBuffer.append(item).append(" ,");
        }
        return stringBuffer.toString();
    }
```
4. 对象
```
/**
     * addCourse2.jsp  action="pojoType" method="post"
     * 封装成一个Course对象
     */
    @RequestMapping(value ="/pojoType")
    public ModelAndView pojoType(Course course) {
        courseDao.add(course);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index2");
        modelAndView.addObject("courses", courseDao.selectAll());
        return modelAndView;
    }
```
5. 集合（List，Set，Map）
```
/**
     * 测试绑定List， List型需要一个包装类
     * 封装成CourseList对象，有一个List类型的属性
     * 一次性添加一个List的Course
     */
    @RequestMapping(value = "listType")
    public ModelAndView listType(CourseList courseList) {
        ModelAndView modelAndView = new ModelAndView();
        for (Course course : courseList.getCourses()) {
            courseDao.add(course);
        }
        modelAndView.setViewName("index2");
        modelAndView.addObject("courses", courseDao.selectAll());
        return modelAndView;
    }
```
```
@RequestMapping(value = "mapType")
    public ModelAndView mapType(CourseMap courseMap) {
        ModelAndView modelAndView = new ModelAndView();
        //取出courseMap里的每个Course放到courseDao
        //遍历Map需要用的它的keySet,这里的Key是String类型,(通过addMap.jsp可以看出这里的keySet是['one','two'])
        for (String key : courseMap.getCourses().keySet()) {
            Course course = courseMap.getCourses().get(key);
            courseDao.add(course);
        }
        modelAndView.setViewName("index2");
        modelAndView.addObject("courses", courseDao.selectAll());
        return modelAndView;
    }
```
```
/**
     * name="courses[0].id"
     * CourseSet的构造方法中必须先添加两个元素
     */
    @RequestMapping(value = "setType")
    public ModelAndView setType(CourseSet courseSet) {
        ModelAndView modelAndView = new ModelAndView();
        for (Course course : courseSet.getCourses()) {
            courseDao.add(course);
        }
        modelAndView.setViewName("index2");
        modelAndView.addObject("courses", courseDao.selectAll());
        return modelAndView;

    }
```
6. JSON
```
    /**
     * 修改jsp传过来的对象的price数据
     * 需要配置消息转换器
     */
    @RequestMapping(value = "/jsonType")
    @ResponseBody
    public  Course jsonType(@RequestBody Course course){
        course.setPrice(course.getPrice()+100);
        return course;
    }

```
```


    $(function(){
        var course = {
            "id":"8",
            "name":"SSM框架整合",
            "price":"200"
        };
        $.ajax({
            url:"jsonType",
            data:JSON.stringify(course),        //解析json数据 封装成对象
            type:"post",
            contentType:"application/json;charse=UTF-8",
            dataType:"json",
            success:function(data){
                alert(data.name+"---"+data.price);
            }
        })
    })
```