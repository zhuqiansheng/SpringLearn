package com.project.school.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.school.common.Const;
import com.project.school.common.ServerResponse;
import com.project.school.mapper.ExerciseRecordMapper;
import com.project.school.mapper.UserMapper;
import com.project.school.pojo.ExerciseRecord;
import com.project.school.pojo.User;
import com.project.school.service.UserService;
import com.project.school.util.DateTimeUtil;
import com.project.school.util.MD5Util;
import com.project.school.vo.ExerciseHistoryVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
   private UserMapper userMapper;
    @Autowired
    private ExerciseRecordMapper exerciseRecordMapper;
    /**
     * 学生登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse<User> login(String username, String password){
        int resultCount = userMapper.checkUsername(username);
        if(resultCount == 0){
            // 用户名不存在
            return ServerResponse.createByErrorMessage("Username does not exist");
        }

        //MD5加密
        String md5Password = MD5Util.MD5EncodeUtf8(password);

        User user = userMapper.selectLogin(username, md5Password);
        if (user == null){
            // 密码错误！
            return ServerResponse.createByErrorMessage("Wrong password!");
        }
        user.setPassword(StringUtils.EMPTY);
        // 登录成功
        return ServerResponse.createBySuccess("Login successful", user);
    }

    @Override
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {
        //防止横向越权，要校验一下这个用户的旧密码，一定要指定是这个用户，
        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld), user.getUsername());
        if(resultCount == 0){
            // 旧密码错误
            return ServerResponse.createByErrorMessage("Old password error");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if(updateCount > 0){
            // 重置密码成功
            return ServerResponse.createBySuccessMessage("Reset password successfully");
        }
        // 重置密码失败
        return ServerResponse.createByErrorMessage("Failed to reset password");
    }

    @Override
    public ServerResponse<User> updateInformation(User user) {
        //username是不能被更新的
        //email也要进行一个校验，校验新的email是不是已经存在，并且存在的email如果是相同的话，不能是我们当前这个用户的
        int resultCount = userMapper.checkEmailByUsername(user.getEmail(), user.getUsername());
        if(resultCount > 0){
            // email已存在，请更换email再尝试更新
            return ServerResponse.createByErrorMessage("Email already exists, please change email and try to update");
        }
        User updateUser = new User();
        updateUser.setUsername(user.getUsername());
        updateUser.setName(user.getName());
        updateUser.setSchool(user.getSchool());
        updateUser.setMajor(user.getMajor());
        updateUser.setEmail(user.getEmail());

        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if(updateCount > 0){
            // 更新个人信息成功
            return ServerResponse.createBySuccess("Update personal information successfully", updateUser);
        }
        // 更新个人信息失败
        return ServerResponse.createByErrorMessage("Failed to update personal information");
    }


    // 后台管理员操作

    @Override
    public ServerResponse<String> resetStudentPassword(String username) {
        int resultCount = userMapper.checkUsername(username);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("Username does not exist！");
        }
        // 限制不能重置其他管理员的密码
        int role = userMapper.checkUserRole(username);
        if(role == Const.Role.ROLE_TEACHER){
            return ServerResponse.createByErrorMessage("No right to modify passwords of other administrators");
        }
        // 初始密码就是学号，即用户名
        String initialPassword = MD5Util.MD5EncodeUtf8(username);
        int result = userMapper.resetStudentPassword(username, initialPassword);
        if(result > 0){
            return ServerResponse.createBySuccessMessage("Reset the student number to" + username + "Password succeeded");
        }
        return ServerResponse.createByErrorMessage("Reset the student number to" + username + "Password failed");
    }

    @Override
    public ServerResponse<PageInfo> getExerciseHistory(User user,int pageNum,int pageSize){
        List<ExerciseHistoryVo> historyVoList=new ArrayList<>();
        //startpage--start
        PageHelper.startPage(pageNum,pageSize);
        //填充自己的sql查询逻辑
        List<ExerciseRecord> historyList=exerciseRecordMapper.selectExcHistory(user.getUsername());
        for(ExerciseRecord exeRecordItem:historyList){
            ExerciseHistoryVo exerciseHistoryVo=assembleExeHistoryVo(user.getUsername(),exeRecordItem);
            historyVoList.add(exerciseHistoryVo);
        }
        //pageHelp-收尾
        PageInfo pageResult=new PageInfo(historyList);
        pageResult.setList(historyVoList);
        //pageHelp收尾应该用上面两行，用下面会出错，必须是数据库获得的结果
//        PageInfo pageResult=new PageInfo(historyVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private ExerciseHistoryVo assembleExeHistoryVo(String username,ExerciseRecord exeRcord){
        ExerciseHistoryVo exerciseHistoryVo=new ExerciseHistoryVo();
        exerciseHistoryVo.setArea(exeRcord.getAreaId());
        exerciseHistoryVo.setStartTime(DateTimeUtil.dateToStr(exeRcord.getCreateTime()));
       int result=exerciseRecordMapper.countFinishCount(username);
        exerciseHistoryVo.setFinishCount(result);
        exerciseHistoryVo.setScore(exeRcord.getScore());
        if(exeRcord.getFinishFlag()==1){
            //完成用时
            String time= DateTimeUtil.getDatePoor(exeRcord.getFinishTime(),exeRcord.getCreateTime());
            exerciseHistoryVo.setUseTime(time);
        }else {
            exerciseHistoryVo.setUseTime("0");
        }
        exerciseHistoryVo.setUsername(exeRcord.getUsername());
        exerciseHistoryVo.setType(exeRcord.getTypeId());
        exerciseHistoryVo.setFinishFlag(exeRcord.getFinishFlag());
        return exerciseHistoryVo;
    }

}
