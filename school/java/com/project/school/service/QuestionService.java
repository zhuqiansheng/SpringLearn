package com.project.school.service;

import com.project.school.common.ServerResponse;
import com.project.school.vo.QuestionVo;

import java.util.List;

/**
 * @author YWD
 * @date 2019/5/17
 */
public interface QuestionService {
    /**
     * 新出题，出题时将出题信息记录进question record
     * @param username 用户名
     * @param num 出题数量
     * @param typeId 1-选择 2-填空
     * @param areaId 对应前端第几个Exercise
     * @return 新出的题
     */
    ServerResponse<List<QuestionVo>> listQuestion(String username, Integer num, Integer typeId, Integer areaId);

    ServerResponse checkFlagAndAreaByUser(String username, Integer areaId);

    ServerResponse<Integer> getScore(String username, Integer areaId, Integer num, String userAnswerListStr);
}
