package com.njupt.controller;
import com.njupt.entity.Staff;
import com.njupt.service.SelfService;
import com.njupt.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * left.jsp中个人中心模块
 * 和 login.jsp
 */

@Controller("selfController")
public class SelfController {
    @Autowired
    private SelfService selfService;

    //被LoginFilter过滤的 页面转发到 toLogin  或者 账号密码输入错误
    public void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }


    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        Staff staff = selfService.login(account, password);

        //账号或密码错误
        if (staff == null) {
            response.sendRedirect("toLogin.jsp");
        }else {
            //登录成功   创建用户session
            HttpSession session = request.getSession();
            session.setAttribute("USER", staff);
            response.sendRedirect("main.do");
        }
    }

    //self/info.do
    public void info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //info 里的USER 是从session获取的
        request.getRequestDispatcher("../info.jsp").forward(request, response);
    }

    public void toChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../change_password.jsp").forward(request, response);
    }

    public void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String oldPassword = request.getParameter("password");
        String newPassword = request.getParameter("password1");
        Staff user = (Staff) request.getSession().getAttribute("USER");
        Integer id = user.getId();
        //判断原密码是否正确
        if (oldPassword == user.getPassword()) {
            selfService.changePassword(id, newPassword);
            response.getWriter().print("<script type=\"text/javascript\">parent.location.href=\"../logout.do\"</script>");
        } else {
            response.sendRedirect("toChangePassword");
        }

    }

    //logout.do
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("USER");
        response.sendRedirect("main.do");
    }

    public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index.jsp");
    }
}
