package com.yun.pattern.template.course;

public class JavaCourse extends NetworkCourse {

    @Override
    protected void checkHomework() {
        System.out.println("检查Java架构师课后作业");
    }


}