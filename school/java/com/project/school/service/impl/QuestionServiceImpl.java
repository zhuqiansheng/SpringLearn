package com.project.school.service.impl;

import com.project.school.common.ServerResponse;
import com.project.school.mapper.ExerciseRecordMapper;
import com.project.school.mapper.QuestionBankMapper;
import com.project.school.pojo.ExerciseRecord;
import com.project.school.pojo.QuestionBank;
import com.project.school.service.QuestionService;
import com.project.school.vo.QuestionVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionBankMapper questionBankMapper;
    @Autowired
    private ExerciseRecordMapper exerciseRecordMapper;

    @Override
    public ServerResponse<List<QuestionVo>> listQuestion(String username, Integer num, Integer typeId, Integer areaId) {
        List<QuestionVo> questionVoList = this.getQuestionVoList(username, num, typeId, areaId);
        if(questionVoList.isEmpty()){
            return ServerResponse.createByErrorMessage("Title generation failed！");
        }
        else {
            return ServerResponse.createBySuccess(questionVoList);
        }
    }

    @Override
    public ServerResponse checkFlagAndAreaByUser(String username, Integer areaId) {
        int resultCount = exerciseRecordMapper.checkCompleteOrNot(username, areaId);
        // resultCount为0有两种情况：1.用户第一次练习该区域的题目； 2.已经练习过且相同区域的练习都已完成
        if(resultCount == 0){
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse<Integer> getScore(String username, Integer areaId, Integer num, String userAnswerListStr) {
        //将用户答案字符串转换为数组类型
        String[] userAnswers = StringUtils.deleteWhitespace(userAnswerListStr).split(";");
       //将用户数组类型答案转化为list类型
        List<String> userAnswerList = Arrays.asList(userAnswers);
        //从题目中获取答案
        List<String> answerList = this.getAnswerList(username, areaId);

        int rightNum = 0;
        //用0、1对应相应题目的对错，0-错 1-对
        StringBuilder stringBuilder = new StringBuilder();
        if(userAnswerList.size() < num){
            // 有未完成的题目
            return ServerResponse.createByErrorMessage("Have unfinished practice question(s)");
        }
        for(int i=0;i <num; i++){
            //todo 此处应该areaId与typeId结合进行判断，日后出现问题再修改，目前areaId==8只有这一中可能，所以不会出错
            if(areaId==8){
                if(userAnswerList.get(i).toUpperCase().equals(answerList.get(i))){
                    rightNum++;
                    stringBuilder.append(1);
                }else{
                    stringBuilder.append(0);
                }
            }else {
                if(userAnswerList.get(i).equals(answerList.get(i))){
                    rightNum++;
                    stringBuilder.append(1);
                }else{
                    stringBuilder.append(0);
                }
            }

        }
        String rightOrWrongFlag = stringBuilder + "";
        int score =(int)Math.round(((double)rightNum/num*100));
        int resultCount = exerciseRecordMapper.updateAnswerInfo(username, rightNum, userAnswerListStr, rightOrWrongFlag, score, areaId);
        if(resultCount == 0){
            // 更新记录失败
            return ServerResponse.createByErrorMessage("Update record failed");
        }
        return ServerResponse.createBySuccess(score);
    }

    private List<QuestionVo> getQuestionVoList(String username, Integer num, Integer typeId, Integer areaId){
        ExerciseRecord exerciseRecord = new ExerciseRecord();
        // 题目信息列表
        List<QuestionVo> questionVoList = new ArrayList<>();
        // 题目编号列表
        List<Integer> questionIdList = new ArrayList<Integer>();
        List<QuestionBank> questionBankList = questionBankMapper.selectQuestion(num, typeId, areaId);
        if(CollectionUtils.isNotEmpty(questionBankList)) {
            Integer typeID=1;
                for (QuestionBank questionItem : questionBankList) {
                    QuestionVo questionVo = new QuestionVo();
                    questionVo.setId(questionItem.getId());
                    questionVo.setQuestion(questionItem.getQuestion());
                    questionVo.setQuestionEn(questionItem.getQuestionEn());
                    questionVo.setQuestionPinyin(questionItem.getQuestionPinyin());
                    questionVo.setOptions(questionItem.getOptions());
                    questionVo.setStyle(questionItem.getQuestionType());
                    Integer type=questionItem.getQuestionType();
                    typeID=type;
                    //加入三个干扰项
                    if((type==2&&areaId==4)||(type==2&&areaId==5)){
                        StringBuilder selectAnswer = new StringBuilder(questionItem.getAnswer().trim());
                        List<String>list=new ArrayList<String>();
                        list.add(questionItem.getAnswer().trim());
                        if(type==2&&areaId==4) {
                            for (int i = 0; i < 3; i++) {
                                String addChose = questionBankMapper.addWrongChose(selectAnswer.toString());
                                list.add(addChose.trim());
                            }
                        }else if(type==2&&areaId==5) {
                            for (int i = 0; i < 3; i++) {
                                String addWrong = questionBankMapper.addWrongAnswer(selectAnswer.toString());
                                list.add(addWrong.trim());
                            }
                        }else {
                            questionVo.setAnswer(questionItem.getAnswer().trim());
                        }
                        Collections.shuffle(list);
                        list.add(questionItem.getAnswer().trim());
                        questionVo.setAnswer(list.toString());

                    } else {
                        questionVo.setAnswer(questionItem.getAnswer().trim());
                    }
                    questionVoList.add(questionVo);
                    questionIdList.add(questionItem.getId());
                }
            // 出题时将题目信息记录到exercise_record表
            String questionIdStr = StringUtils.join(questionIdList, ",");
            exerciseRecord.setUsername(username);
            exerciseRecord.setNum(num);
            exerciseRecord.setQuestionIdList(questionIdStr);
            exerciseRecord.setScore(0);
            exerciseRecord.setAreaId(areaId);
            exerciseRecord.setFinishFlag(0);
            exerciseRecord.setTypeId(typeID);
            exerciseRecordMapper.insert(exerciseRecord);
        }
        return questionVoList;
    }

    private List<String> getAnswerList(String username, Integer areaId){
        List<String> answerList = new ArrayList<>();
        List<Integer> questionIdList = new ArrayList<>();
        //从question record中获取出的题目的id
        String questionIdStr = exerciseRecordMapper.selectQuestionIdList(username, areaId);
        String questionIdStrList[] = questionIdStr.split(",");
        for(String questionId : questionIdStrList){
            questionIdList.add(Integer.parseInt(questionId));
        }
        //通过题目id从题库中获取答案
        for(Integer questionId : questionIdList){
            String answer = questionBankMapper.getAnswer(questionId);
            answerList.add(answer);
        }
        return answerList;
    }

}
