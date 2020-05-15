package com.gupao.vip.pattern.template.course;

/**
 * Created by qingbowu on 2019/3/18.
 */
public class BigDataCourse extends NetworkCourse {

    private boolean needHomework = false;

    public BigDataCourse(boolean needHomework) {
        this.needHomework = needHomework;
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
