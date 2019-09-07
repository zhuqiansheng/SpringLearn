package com.project.school.controller.backend;

import com.project.school.common.Const;
import com.project.school.common.ResponseCode;
import com.project.school.common.ServerResponse;
import com.project.school.pojo.User;
import com.project.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by YWD on 2019/3/30.
 */
@Controller
@RequestMapping("/manage/")
public class UserManageController {

    @Autowired
    private UserService userService;

    /**
     * 管理员登录
     * @param map
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(@RequestBody Map<String, String> map, HttpSession session){
        String username = map.get("username");
        String password = map.get("password");
        ServerResponse<User> response = userService.login(username, password);
        if(response.isSuccess()){
            User user = response.getData();
            if(user.getRole() == Const.Role.ROLE_TEACHER){
                session.setAttribute(Const.CURRENT_USER, user);
                return response;
            }else{
                return ServerResponse.createByErrorMessage("Not an administrator, can't log in");
            }
        }
        return response;
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccessMessage("exit successfully");
    }

    /**
     * 修改管理员自己的密码
     * @param map
     * @param session
     * @return
     */
    @RequestMapping(value = "reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(@RequestBody Map<String, String> map, HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("The administrator is not logged in");
        }
        String passwordOld = map.get("passwordOld");
        String passwordNew = map.get("passwordNew");
        return userService.resetPassword(passwordOld, passwordNew, user);
    }

    /**
     * 重置学生的密码
     * @param username
     * @param session
     * @return
     */
    @RequestMapping(value = "reset_student_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetStudentPassword(String username, HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("The administrator is not logged in");
        }
        return userService.resetStudentPassword(username);
    }

    // TODO: 2019/6/3 获取自己所带学生的部分个人信息 （通过学生id获取）
    // TODO: 2019/6/3 获取所带的所有班级
    // TODO: 2019/6/3 获取某班级下全部学生username（相当于学生id，供按学号查找做题记录）
    /**
     * 通过stuId查看对应学生做题历史记录
     * @param session
     * @param stuUsername
     * @return
     */
    // TODO: 2019/6/3 通过stuId查看具体学生的成绩记录 
    @RequestMapping(value = "check_stu_record_history.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse checkStuHistoryBystuId(HttpSession session,String stuUsername){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            ServerResponse.createByErrcoCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"Need to log in with administrator privileges！");
        }
        return null;
    }
// TODO: 2019/6/3 查看自己所带所有学生的做题历史记录 
}
