package com.njupt.controller;

import com.njupt.entity.Department;
import com.njupt.entity.Staff;
import com.njupt.service.DepartmentService;
import com.njupt.service.StaffService;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resources;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller("staffController")
public class StaffController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private DepartmentService departmentService;


    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Staff> staffList = staffService.getAll();
        request.setAttribute("LIST", staffList);
        request.getRequestDispatcher("../staff_list.jsp").forward(request, response);
    }

    public void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取部门列表 供用户选择  而不是输入部门id
        List<Department> departmentList = departmentService.getAll();
        request.setAttribute("DLIST", departmentList);
        request.getRequestDispatcher("../staff_add.jsp").forward(request, response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String account = request.getParameter("account");
        Integer did =Integer.parseInt(request.getParameter("did"));
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String idNumber = request.getParameter("idNumber");
        String info = request.getParameter("info");
        Date bornDate = null;
        try {
            bornDate = new SimpleDateFormat("yyyy--MM--dd").parse(
                    request.getParameter("bornDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Staff staff = new Staff();
        staff.setAccount(account);
        staff.setDid(did);
        staff.setName(name);
        staff.setSex(sex);
        staff.setIdNumber(idNumber);
        staff.setInfo(info);
        staff.setBornDate(bornDate);

        staffService.add(staff);
        response.sendRedirect("list.do");
    }

    public void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //传到toEdit.do的 数据有 id
        //staff_edit.jsp 页面需要 被编辑的OBJ
        Integer id = Integer.parseInt(request.getParameter("id"));
        Staff staff = staffService.get(id);

        request.setAttribute("OBJ", staff);
        request.getRequestDispatcher("../staff_edit.jsp").forward(request, response);
    }

    public void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String idNumber = request.getParameter("idNumber");
        String info =request.getParameter("info");
        Date bornDate=null;
        try {
            bornDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("bornDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer did = Integer.parseInt(request.getParameter("did"));

        Staff staff = staffService.get(id);
        staff.setInfo(info);
        staff.setBornDate(bornDate);
        staff.setIdNumber(idNumber);
        staff.setDid(did);
        staff.setAccount(account);
        staff.setName(name);
        staff.setSex(sex);

        response.sendRedirect("list.do");
    }

    public void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //staff_list 传过来的是 id
        Integer id = Integer.parseInt(request.getParameter("id"));
        staffService.remove(id);
        response.sendRedirect("list.do");
    }

    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //staff_list 传过来的是 id
        //数据发送到staff_detail.jsp  需要detail的对象 OBJ
        Integer id = Integer.parseInt(request.getParameter("id"));
        Staff staff = staffService.get(id);
        request.setAttribute("OBJ", staff);
        request.getRequestDispatcher("../staff_detail.jsp").forward(request, response);

    }
}
