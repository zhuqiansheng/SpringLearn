package com.project.school.vo;

import java.util.Date;

public class ExerciseHistoryVo {

    /**
     *用于向前端返回历史成绩
     */
    //习题生成时间
    private String startTime;
    //做题用时
    private String useTime;
    //得分
    private Integer score;
    //章节
    private Integer area;
    //类型
    private Integer type;
    //练习次数 1-选择 2-填空
    private Integer finishCount;
    //练习者
    private String username;
    //练习完成标志1-finish 0-no
    private Integer finishFlag;
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Integer finishCount) {
        this.finishCount = finishCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getFinishFlag() {
        return finishFlag;
    }

    public void setFinishFlag(Integer finishFlag) {
        this.finishFlag = finishFlag;
    }


}
