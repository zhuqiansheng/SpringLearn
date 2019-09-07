package com.njupt.controller;

import com.njupt.entity.Department;
import com.njupt.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller("departmentController")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取部门列表
     */
    //   /department/list.do     /department_list.jsp
    public void list(HttpServletRequest request, HttpServletResponse response) {
        //获取部门列表
        List<Department> departmentList = departmentService.getAll();
        //放入request
        request.setAttribute("LIST", departmentList);
        try {
            //转发给department_list.jsp
            request.getRequestDispatcher("../department_list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到 添加 页面
     */
    public void toAdd(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("../department_add.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从add页面 获取属性值 并更新到 数据库
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //从department_add.jsp 页面的输入框 的 name 属性获取value
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        Department department = new Department();
        department.setName(name);
        department.setAddress(address);
        //添加
        departmentService.add(department);
        //重定向
        response.sendRedirect("list.do");

    }

    /**
     * 从 list 页面跳转到 edit 页面
     */
    public void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../department_edit.jsp").forward(request, response);
    }

    /**
     * 从edit页面 获取 属性值 并更新数据库
     */
    public void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取 department_edit 页面 form 下的 id
        Integer id = Integer.parseInt(request.getParameter("id"));

        //获取输入的属性值
        String name = request.getParameter("name");
        String address = request.getParameter("address");

        //设置新属性
        Department department = new Department();
        department.setName(name);
        department.setAddress(address);
        department.setId(id);

        //更新
        departmentService.edit(department);
        //回到
        response.sendRedirect("list.do");
    }

    public void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));

        departmentService.remove(id);

        response.sendRedirect("list.do");
    }


}
