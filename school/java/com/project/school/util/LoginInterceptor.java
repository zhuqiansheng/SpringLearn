package com.project.school.util;

import com.project.school.common.Const;
import com.project.school.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by qyh on 2019/3/31.
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 在拦截点执行前拦截，如果返回true则不执行拦截点后的操作（拦截成功）
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(session.getAttribute(Const.CURRENT_USER)!=null){
//        if(user!=null){
            // 登录成功不拦截
            return true;
        }else{
            // 拦截后进入登录界面
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // 在处理过程中，执行拦截
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // 执行完毕，返回前拦截
    }
}
