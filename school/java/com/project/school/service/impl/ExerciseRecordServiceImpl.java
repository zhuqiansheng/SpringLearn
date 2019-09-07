package com.project.school.service.impl;

import com.project.school.common.ServerResponse;
import com.project.school.mapper.ExerciseRecordMapper;
import com.project.school.mapper.QuestionBankMapper;
import com.project.school.pojo.QuestionBank;
import com.project.school.service.ExerciseRecordService;
import com.project.school.vo.QuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by qyh on 2019/3/19.
 */
@Service("exerciseRecordService")
public class ExerciseRecordServiceImpl implements ExerciseRecordService {

    @Autowired
    private ExerciseRecordMapper exerciseRecordMapper;
    @Autowired
    private QuestionBankMapper questionBankMapper;

    @Override
    public ServerResponse<List<QuestionVo>> listQuestion(String username, Integer areaId,Integer typeId) {
        List<QuestionVo> questionVoList = new ArrayList<>();
        List<Integer> questionIdList = new ArrayList<>();
        //问题ID的字符串26,183,7,193,32
        String questionIdStr = exerciseRecordMapper.selectQuestionIdList(username, areaId);
        //将id的字符串切割为list,26 183 7 193 32
        String[] questionIdStrList = questionIdStr.split(",");
        //转换为Integer类型的list
        for(String questionId : questionIdStrList){
            questionIdList.add(Integer.parseInt(questionId));
        }
        //通过问题id将所有题目装入questionVoList
        for(Integer questionId : questionIdList){
            QuestionVo questionVo = new QuestionVo();
            QuestionBank questionItem = questionBankMapper.selectByPrimaryKey(questionId);
            questionVo.setId(questionId);
            questionVo.setQuestion(questionItem.getQuestion());
            questionVo.setQuestionEn(questionItem.getQuestionEn());
            questionVo.setQuestionPinyin(questionItem.getQuestionPinyin());
            questionVo.setOptions(questionItem.getOptions());
            questionVo.setStyle(questionItem.getQuestionType());
            Integer type=questionItem.getQuestionType();
            if((type==2&&areaId==4)||(type==2&&areaId==5)){
                StringBuilder selectAnswer=new StringBuilder(questionItem.getAnswer().trim());
                List<String>list=new ArrayList<String>();
                list.add(questionItem.getAnswer().trim());
                if(type==2&&areaId==4){
                    for (int i=0;i<3;i++){
                        String addChose=questionBankMapper.addWrongChose(selectAnswer.toString());
                        list.add(addChose.trim());
                    }
                }else if(type==2&&areaId==5){
                    for (int i = 0; i < 3; i++) {
                        String addWrong = questionBankMapper.addWrongAnswer(selectAnswer.toString());
                        list.add(addWrong.trim());
                    }
                } else {
                    questionVo.setAnswer(questionItem.getAnswer().trim());
                }
//                打乱答案的顺序
                Collections.shuffle(list);
                list.add(questionItem.getAnswer().trim());
                questionVo.setAnswer(list.toString());
            }else {
                questionVo.setAnswer(questionItem.getAnswer().trim());
            }
            questionVoList.add(questionVo);
        }
        // 您还有未完成的题目，请继续完成
        return ServerResponse.createBySuccess("You have unfinished questions, please continue to complete!",questionVoList);
    }

    /*@Override
    public ServerResponse updateAnswerInfo(String username, Integer rightNum, String userAnswerList, String rightOrWrongFlag) {
        int resultCount = exerciseRecordMapper.updateAnswerInfo(username, rightNum, userAnswerList, rightOrWrongFlag);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createBySuccess("更新成功");
    }*/
}
