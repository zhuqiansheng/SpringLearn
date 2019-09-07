package com.project.school.pojo;

public class QuestionBank {
    private Integer id;

    private String question;

    private String questionEn;

    private String questionPinyin;

    private String options;

    private String answer;

    private Integer questionType;

    private Integer questionArea;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getQuestionEn() {
        return questionEn;
    }

    public void setQuestionEn(String questionEn) {
        this.questionEn = questionEn == null ? null : questionEn.trim();
    }

    public String getQuestionPinyin() {
        return questionPinyin;
    }

    public void setQuestionPinyin(String questionPinyin) {
        this.questionPinyin = questionPinyin == null ? null : questionPinyin.trim();
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options == null ? null : options.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public Integer getQuestionArea() {
        return questionArea;
    }

    public void setQuestionArea(Integer questionArea) {
        this.questionArea = questionArea;
    }
}