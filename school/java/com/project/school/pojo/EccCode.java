package com.project.school.pojo;

public class EccCode {
    private Integer id;

    private String name;

    private String vocalCode;

    private String shapeCode1;

    private String shapeCode2;

    private String shapeCode3;

    private String standardCode;

    private String faultToleranceCode1;

    private String faultToleranceCode2;

    private String faultToleranceCode3;

    private String shortCode1;

    private String shortCode2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getVocalCode() {
        return vocalCode;
    }

    public void setVocalCode(String vocalCode) {
        this.vocalCode = vocalCode == null ? null : vocalCode.trim();
    }

    public String getShapeCode1() {
        return shapeCode1;
    }

    public void setShapeCode1(String shapeCode1) {
        this.shapeCode1 = shapeCode1 == null ? null : shapeCode1.trim();
    }

    public String getShapeCode2() {
        return shapeCode2;
    }

    public void setShapeCode2(String shapeCode2) {
        this.shapeCode2 = shapeCode2 == null ? null : shapeCode2.trim();
    }

    public String getShapeCode3() {
        return shapeCode3;
    }

    public void setShapeCode3(String shapeCode3) {
        this.shapeCode3 = shapeCode3 == null ? null : shapeCode3.trim();
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode == null ? null : standardCode.trim();
    }

    public String getFaultToleranceCode1() {
        return faultToleranceCode1;
    }

    public void setFaultToleranceCode1(String faultToleranceCode1) {
        this.faultToleranceCode1 = faultToleranceCode1 == null ? null : faultToleranceCode1.trim();
    }

    public String getFaultToleranceCode2() {
        return faultToleranceCode2;
    }

    public void setFaultToleranceCode2(String faultToleranceCode2) {
        this.faultToleranceCode2 = faultToleranceCode2 == null ? null : faultToleranceCode2.trim();
    }

    public String getFaultToleranceCode3() {
        return faultToleranceCode3;
    }

    public void setFaultToleranceCode3(String faultToleranceCode3) {
        this.faultToleranceCode3 = faultToleranceCode3 == null ? null : faultToleranceCode3.trim();
    }

    public String getShortCode1() {
        return shortCode1;
    }

    public void setShortCode1(String shortCode1) {
        this.shortCode1 = shortCode1 == null ? null : shortCode1.trim();
    }

    public String getShortCode2() {
        return shortCode2;
    }

    public void setShortCode2(String shortCode2) {
        this.shortCode2 = shortCode2 == null ? null : shortCode2.trim();
    }
}