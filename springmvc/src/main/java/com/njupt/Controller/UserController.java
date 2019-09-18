package com.njupt.Controller;


import com.njupt.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

//  配置路由

    //更新密码需要登录
    @RequestMapping("/updatepsw")
    public String updatepsw() {
        return "user/updatepsw";
    }

    //登录成功页面
    @RequestMapping("/success_login")
    public String success() {
        return "user/success";
    }




}

