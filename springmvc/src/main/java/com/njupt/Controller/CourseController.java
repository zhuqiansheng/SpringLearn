package com.njupt.Controller;

import com.njupt.Dao.CourseDao;
import com.njupt.entity.Course;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


@Controller
public class CourseController {
    @Resource(name="CourseDao")
    private CourseDao courseDao;

    @GetMapping(value= "/getAll")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("courses", courseDao.selectAll());
        //在 index.jsp 展示
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /**
     * 添加课程
     * action="${pageContext.request.contextPath}/add" method="post"
     * @param course :数据绑定后得到的course对象
     */
    @PostMapping(value= "/add")
    public String addCourse(Course course) {
        courseDao.add(course);
        //重定向到getAll页面
        return "redirect:/getAll";
    }

    /**
     * 编辑
     * action="${pageContext.request.contextPath}/getById/${course.id}" method="get"
     * @param id  课程id
     */
    @GetMapping(value= "/getById/{id}")
    //将 页面传过来的id赋值给 形参id
    public ModelAndView getById(@PathVariable(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("course", courseDao.select(id));
        modelAndView.setViewName("edit");
        return modelAndView;
    }

    /**
     * action="${pageContext.request.contextPath}/update" method="post"
     * @return
     */
    @PutMapping(value= "update")
    public String update(Course course) {
        courseDao.update(course);
        //修改完成后重定向到index页面
        return "redirect:/getAll";
    }

    /**
     * action="${pageContext.request.contextPath}/delete/${course.id}" method="post"
     * @param id
     * @return
     */
    @DeleteMapping(value= "delete/{id}")
    public String delete(@PathVariable(value = "id") int id) {
        courseDao.delete(id);
        return "redirect:/getAll";
    }
}
