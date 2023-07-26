package com.powernode.mybatis.bean;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class Class {
    private Integer classNo;
    private String className;

    public Class() {}

    @Override
    public String toString() {
        return "Class{" +
                "classNo=" + classNo +
                ", className='" + className + '\'' +
                '}';
    }

    public Integer getClassNo() {
        return classNo;
    }

    public void setClassNo(Integer classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Class(Integer classNo, String className) {
        this.classNo = classNo;
        this.className = className;
    }
}
