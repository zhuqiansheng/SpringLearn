package com.project.school.vo;

import java.util.List;

/**
 * Created by qyh on 2019/3/20.
 */
public class AnswerVo {
    private Integer rightNum;
    private List<String> userAnswerList;
    private String rightOrWrongFlag;
    private  Integer score;

    public Integer getRightNum() {
        return rightNum;
    }

    public void setRightNum(Integer rightNum) {
        this.rightNum = rightNum;
    }

    public List<String> getUserAnswerList() {
        return userAnswerList;
    }

    public void setUserAnswerList(List<String> userAnswerList) {
        this.userAnswerList = userAnswerList;
    }

    public String getRightOrWrongFlag() {
        return rightOrWrongFlag;
    }

    public void setRightOrWrongFlag(String rightOrWrongFlag) {
        this.rightOrWrongFlag = rightOrWrongFlag;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
