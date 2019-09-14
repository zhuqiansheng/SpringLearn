package com.njupt.controller;

import com.njupt.entity.Log;
import com.njupt.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * left.jsp中日志信息模块
 * 获取被点击的类型的日志 转发并跳转到 log_list.jsp
 * log_list.jsp 获取TYPE(日志类型)  和 LIST (日志列表)
 */

@Controller("logController")
public class LogController {
    @Autowired
    private LogService logService;

    /**
     *     <a href="log/operationLog.do"
     */
    public void operationLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> logList=logService.getOperationLog();
        request.setAttribute("TYPE", "操作");
        request.setAttribute("LIST", logList);

        request.getRequestDispatcher("../log_list.jsp").forward(request, response);
    }

    /**
     *     <a href="log/loginLog.do"
     */
    public void loginLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> logList=logService.getLoginLog();
        request.setAttribute("TYPE", "登录");
        request.setAttribute("LIST", logList);

        request.getRequestDispatcher("../log_list.jsp").forward(request, response);
    }

    /**
     *     <a href="log/systemLog.do"
     */
    public void systemLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> logList=logService.getSystemLog();
        request.setAttribute("TYPE", "系统");
        request.setAttribute("LIST", logList);

        request.getRequestDispatcher("../log_list.jsp").forward(request, response);
    }
}
