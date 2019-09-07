package com.project.school.vo;

public class QuestionVo {
    private Integer id;
    private String question; // 题干
    private String questionEn;  // 题干的英文
    private String questionPinyin;  // 题干的拼音
    private String options; // 选项
    private String answer; // 答案
    private Integer style;//题型

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionEn() {
        return questionEn;
    }

    public void setQuestionEn(String questionEn) {
        this.questionEn = questionEn;
    }

    public String getQuestionPinyin() {
        return questionPinyin;
    }

    public void setQuestionPinyin(String questionPinyin) {
        this.questionPinyin = questionPinyin;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
