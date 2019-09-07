package com.project.school.pojo;

import java.util.Date;

public class ExerciseRecord {
    private Integer id;

    private String username;

    private Integer num;

    private String questionIdList;

    private Integer rightNum;

    private String userAnswerList;

    private String rightOrWrong;

    private Integer score;

    private Date createTime;

    private Date finishTime;

    private Integer areaId;

    private Integer finishFlag;

    private Integer typeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getQuestionIdList() {
        return questionIdList;
    }

    public void setQuestionIdList(String questionIdList) {
        this.questionIdList = questionIdList == null ? null : questionIdList.trim();
    }

    public Integer getRightNum() {
        return rightNum;
    }

    public void setRightNum(Integer rightNum) {
        this.rightNum = rightNum;
    }

    public String getUserAnswerList() {
        return userAnswerList;
    }

    public void setUserAnswerList(String userAnswerList) {
        this.userAnswerList = userAnswerList == null ? null : userAnswerList.trim();
    }

    public String getRightOrWrong() {
        return rightOrWrong;
    }

    public void setRightOrWrong(String rightOrWrong) {
        this.rightOrWrong = rightOrWrong == null ? null : rightOrWrong.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getFinishFlag() {
        return finishFlag;
    }

    public void setFinishFlag(Integer finishFlag) {
        this.finishFlag = finishFlag;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}