package com.yun.pattern.observer.gper;


import java.util.Observable;
import java.util.Observer;

/**
 * @className: Teacher <br/>
 * @description: <br/>
 * @date: 2020/5/28 0:00 <br/>
 * @author: yun <br/>
 */
public class Teacher implements Observer {

    private String name;
    public Teacher(String name){
        this.name = name;
    }

    public void update(Observable o, Object arg) {
        GPer gper = (GPer)o;
        Question question = (Question) arg;
        System.out.println("=====================");
        System.out.println(name + "老师，你好！\n" +
                "您收到了一个来自“" + gper.getName() + "”的提问，希望您解答，问题内容如下：\n" +
                question.getContent() + "\n" +
                "提问者：" + question.getUserName());
    }

}
