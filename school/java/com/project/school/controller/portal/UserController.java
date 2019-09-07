package com.project.school.controller.portal;

import com.project.school.common.Const;
import com.project.school.common.ResponseCode;
import com.project.school.common.ServerResponse;
import com.project.school.pojo.User;
import com.project.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    // TODO: 2019/5/9 登录限制
    
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(@RequestBody Map<String, String> map, HttpSession session){
        String username = map.get("username");
        String password = map.get("password");
        ServerResponse<User> response = userService.login(username, password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, response.getData());
            session.setMaxInactiveInterval(1800);
        }
        return response;
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        // 退出成功
        return ServerResponse.createBySuccessMessage("Exit successfully");
    }

    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user != null){
            return ServerResponse.createBySuccess(user);
        }
        // 用户未登录，无法获取当前用户的信息
        return ServerResponse.createByErrorMessage("The user is not logged in and cannot obtain information about the current user.");
    }

    @RequestMapping(value = "reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(@RequestBody Map<String, String> map, HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            // 用户未登录
            return ServerResponse.createByErrorMessage("User not logged in");
        }
        String passwordOld = map.get("passwordOld");
        String passwordNew = map.get("passwordNew");
        return userService.resetPassword(passwordOld, passwordNew, user);
    }

    @RequestMapping(value = "update_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateInformation(@RequestBody User user, HttpSession session){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            // 用户未登录
            return ServerResponse.createByErrorMessage("User not logged in");
        }
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> response = userService.updateInformation(user);
        if(response.isSuccess()){
            response.getData().setUsername(currentUser.getUsername());
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    @RequestMapping(value = "check_exercise_record.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse checkExerciseRecord(HttpSession session,@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "1") int pageSize){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrcoCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"NEED LOGIN!");
        }
        // TODO: 2019/5/27 使用pagehelp 实现用户练习记录的获取（默认所有），按时间顺序获取（默认降序）、按类型获取、按区域获取

        return userService.getExerciseHistory(user,pageNum,pageSize);
    }


}
