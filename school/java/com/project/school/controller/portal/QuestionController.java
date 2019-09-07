package com.project.school.controller.portal;

import com.project.school.common.Const;
import com.project.school.common.ServerResponse;
import com.project.school.pojo.User;
import com.project.school.service.ExerciseRecordService;
import com.project.school.service.QuestionService;
import com.project.school.vo.QuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by qyh on 2019/3/18.
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private ExerciseRecordService exerciseRecordService;

    @RequestMapping(value = "list_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<QuestionVo>> listQuestion(@RequestBody Map<String, Integer> map, HttpSession session){
        Integer num = map.get("num");
        Integer typeId = map.get("typeId");
        Integer areaId = map.get("areaId");
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            // 用户未登录
            return ServerResponse.createByErrorMessage("User not logged in");
        }
        // 如果在相同区域有未完成的题目，则继续作答
        //  其他区域有未完成的题目，不影响当前区域
        /* 如声母韵母部分有未完成的题目，但是现在要做基本笔画部分的题目，则是重新出题，
           但当再次做声母韵母区域的题目时，则继续上次未完成的 */
        if(questionService.checkFlagAndAreaByUser(user.getUsername(), areaId).isSuccess()){
            // 继续做题，之前的作答作废，需要重新开始
            return exerciseRecordService.listQuestion(user.getUsername(), areaId,typeId);
        }else{
            // 重新出题，出题时将出题信息记录进question record
            return questionService.listQuestion(user.getUsername(), num, typeId, areaId);
        }
    }

    @RequestMapping(value = "get_score.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Integer> getScore(@RequestBody Map<String, String> map, HttpSession session){
        Integer areaId = Integer.parseInt(map.get("areaId"));
        Integer num = Integer.parseInt(map.get("num"));
        String userAnswerListStr = (String)map.get("userAnswerListStr");
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            // 用户未登录
            return ServerResponse.createByErrorMessage("User not logged in");
        }
        return questionService.getScore(user.getUsername(), areaId, num, userAnswerListStr);
    }
}
