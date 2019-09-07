package com.project.school.common;

/**
 * Created by qyh on 2019/3/13.
 */
public class Const {
    public static final String CURRENT_USER = "currentUser";

    public interface Role{
        int ROLE_STUDENT = 0; //学生
        int ROLE_TEACHER = 1; //教师
    }

    public interface QuestionType{
        int TYPE_ONE = 1; //选择
        int TYPE_TWO = 2; //填空
    }

    public interface QuestionArea{
        int AREA_ONE = 1;
        int AREA_TWO = 2;
        int AREA_THREE = 3;
        int AREA_FOUR = 4;
        int AREA_FIVE = 5;
        int AREA_SIX = 6;
        int AREA_SEVEN = 7;
        int AREA_EIGHT = 8;
        int AREA_NINE = 9;
        int AREA_TEN = 10;
    }
}
