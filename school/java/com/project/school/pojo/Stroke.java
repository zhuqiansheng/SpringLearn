package com.project.school.pojo;

public class Stroke {
    private Integer id;

    private String basic26Img;

    private String simple5Img;

    private String basic26Name;

    private String basic26Code;

    private String simple5Name;

    private String simple5Code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBasic26Img() {
        return basic26Img;
    }

    public void setBasic26Img(String basic26Img) {
        this.basic26Img = basic26Img == null ? null : basic26Img.trim();
    }

    public String getSimple5Img() {
        return simple5Img;
    }

    public void setSimple5Img(String simple5Img) {
        this.simple5Img = simple5Img == null ? null : simple5Img.trim();
    }

    public String getBasic26Name() {
        return basic26Name;
    }

    public void setBasic26Name(String basic26Name) {
        this.basic26Name = basic26Name == null ? null : basic26Name.trim();
    }

    public String getBasic26Code() {
        return basic26Code;
    }

    public void setBasic26Code(String basic26Code) {
        this.basic26Code = basic26Code == null ? null : basic26Code.trim();
    }

    public String getSimple5Name() {
        return simple5Name;
    }

    public void setSimple5Name(String simple5Name) {
        this.simple5Name = simple5Name == null ? null : simple5Name.trim();
    }

    public String getSimple5Code() {
        return simple5Code;
    }

    public void setSimple5Code(String simple5Code) {
        this.simple5Code = simple5Code == null ? null : simple5Code.trim();
    }
}