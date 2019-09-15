package com.njupt.Controller;

import com.njupt.Dao.CourseDao;
import com.njupt.entity.Course;
import com.njupt.entity.CourseList;
import com.njupt.entity.CourseMap;
import com.njupt.entity.CourseSet;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DataBindController {
    @Autowired
    private CourseDao courseDao;

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

    /**
     * http://localhost:8080/packageType?text=zzzzz
     * 包装类型
     */
    @RequestMapping(value= "/packageType")
    @ResponseBody
    public String packageType(@RequestParam(value = "text") String text) {
        return "text:" + text;
    }

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










}
