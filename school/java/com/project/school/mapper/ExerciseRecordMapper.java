package com.project.school.mapper;

import com.project.school.pojo.ExerciseRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExerciseRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExerciseRecord record);

    int insertSelective(ExerciseRecord record);

    ExerciseRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExerciseRecord record);

    int updateByPrimaryKey(ExerciseRecord record);

    int checkCompleteOrNot(@Param("username") String username, @Param("areaId") Integer areaId);

    String selectQuestionIdList(@Param("username") String username, @Param("areaId") Integer areaId);

    int updateAnswerInfo(@Param("username") String username, @Param("rightNum") Integer rightNum,
                         @Param("userAnswerList") String userAnswerList,
                         @Param("rightOrWrongFlag") String rightOrWrongFlag, @Param("score") Integer score,
                         @Param("areaId") Integer areaId);

    /**
     * 获取做题历史记录
     * @param username
     * @return
     */
    List<ExerciseRecord> selectExcHistory(String username);

    /**
     * 获取某用户做题完成次数（即做完次数）
     * @param username
     * @return
     */
    int countFinishCount(String username);

}