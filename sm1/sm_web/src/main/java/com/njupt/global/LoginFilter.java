package com.njupt.global;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //获取请求路径
        String path = request.getServletPath();
        //如果是 登录页面的话  放行
        if (path.toLowerCase().contains("login")) {
            chain.doFilter(request, response);
        } else {
            //如果不是登录页面， 查看有没有用户登录后的session
            HttpSession session = request.getSession();
            Object obj = session.getAttribute("USER");
            if (obj != null) {
                chain.doFilter(request, response);
            }else {
                //跳转到登录页面
                response.sendRedirect(request.getContextPath()+"toLogin.do");
            }
        }
    }

    public void destroy() {

    }
}
