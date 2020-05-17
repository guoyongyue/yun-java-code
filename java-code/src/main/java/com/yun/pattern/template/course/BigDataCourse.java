package com.yun.pattern.template.course;

public class BigDataCourse extends NetworkCourse {

    private Boolean needHomework=false;

    public BigDataCourse(Boolean needHomework){
        this.needHomework= needHomework;
    }

    @Override
    protected void checkHomework() {
        System.out.println("检查大数据课程课后作业");
    }

    @Override
    protected boolean needHomework() {
        return this.needHomework;
    }
}
