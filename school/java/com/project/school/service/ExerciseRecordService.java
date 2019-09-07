package com.project.school.service;


import com.project.school.common.ServerResponse;
import com.project.school.vo.QuestionVo;

import java.util.List;

/**
 * @author Created by qyh on 2019/3/19.
 */
public interface ExerciseRecordService {
    /**
     * 非重新出题，获取之前习题记录
     * @param username 用户名
     * @param areaId 练习区域
     * @param typeId 练习类型
     * @return 之前保存的习题记录
     */
    ServerResponse<List<QuestionVo>> listQuestion(String username, Integer areaId,Integer typeId);

//    ServerResponse updateAnswerInfo(String username, Integer rightNum, String userAnswerList, String rightOrWrongFlag);
}
