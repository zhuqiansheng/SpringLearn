package com.njupt.Controller;

import com.njupt.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class BeforeLoginUserController {

    /**
     * 这个页面不能被登录拦截，否则会一直重定向
     */

    @RequestMapping("/login")
    public String login() {
        return "user/login";
    }

    //输入过账号密码后
    @RequestMapping("/logined")
    public String logined(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        System.out.println("username:"+username);
        System.out.println("password:" + password);
        if ("zqs".equals(username) && "123456".equals(password)) {
            User user = new User(username, password);
            session.setAttribute("session_user", user);
            System.out.println("success");
            return "redirect:user/success_login";
        }
         //重定向到另一个路由
        return "redirect:login";
    }


}
