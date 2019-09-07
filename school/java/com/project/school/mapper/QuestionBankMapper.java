package com.project.school.mapper;

import com.project.school.pojo.QuestionBank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionBankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionBank record);

    int insertSelective(QuestionBank record);

    QuestionBank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionBank record);

    int updateByPrimaryKey(QuestionBank record);

    List<QuestionBank> selectQuestion(@Param("num") Integer num, @Param("typeId") Integer typeId, @Param("areaId") Integer areaId);

    String getAnswer(Integer id);

    /**
     * 用于2.3节练习题-填空增加干扰选项
     * @param selectAnswer 正确答案，错误选项中避免出现
     * @return
     */
    String addWrongChose(String selectAnswer);

    /**
     * 用于2.4.1节练习题-填空增加干扰选项
     * @param selectAnswer 正确答案，错误选项中避免出现
     * @return
     */
    String addWrongAnswer(String selectAnswer);

    /**
     * 2.4.3节练习-填空题，由于数据库总共就四个数据，所以直接返回不用随机挑选
     * @param num
     * @return
     */
    String addWrong(int num);
}