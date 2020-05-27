package com.yun.pattern.observer.gper;

/**
 * @className: Main <br/>
 * @description: <br/>
 * @date: 2020/5/27 23:58 <br/>
 * @author: yun <br/>
 */
public class Main {

    public static void main(String[] args) {
        GPer gPer = GPer.getInstance();

        Teacher tom = new Teacher("tom");

        gPer.addObserver(tom);

        Question question = new Question();

        question.setUserName("小钻风");
        question.setContent("你经历了多少风雨");

        gPer.publishQuestion(question);
    }

}
