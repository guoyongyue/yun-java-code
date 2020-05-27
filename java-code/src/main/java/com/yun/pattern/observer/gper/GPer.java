package com.yun.pattern.observer.gper;

import java.util.Observable;

/**
 * @className: GPer <br/>
 * @description: <br/>
 * @date: 2020/5/27 23:58 <br/>
 * @author: yun <br/>
 */
public class GPer extends Observable {

    private String name = "GPer生态圈";

    private static GPer gPer = null;

    public GPer() { }

    public static GPer getInstance() {
        if (gPer == null) {
            gPer = new GPer();
        }
        return gPer;
    }

    public String getName() {
        return name;
    }

    //  发布问题
    public void publishQuestion(Question question) {
        System.out.println(question.getUserName() + "在" + this.name + "上提交了一个问题。");
        setChanged();
        notifyObservers(question);
    }

}
