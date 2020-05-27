package com.yun.pattern.observer.events;

/**
 * @className: Main <br/>
 * @description: <br/>
 * @date: 2020/5/28 0:08 <br/>
 * @author: yun <br/>
 */
public class Main {

    public static void main(String[] args) {
        MouseEventCallback callback = new MouseEventCallback();

        //注册事件
        Mouse mouse = new Mouse();
//        mouse.addLisenter(MouseEventType.ON_CLICK, callback);
//        mouse.addLisenter(MouseEventType.ON_MOVE, callback);
//        mouse.addLisenter(MouseEventType.ON_WHEEL, callback);
//        mouse.addLisenter(MouseEventType.ON_OVER, callback);

        //调用方法
//        mouse.click();
        //失焦事件
//        mouse.blur();


        mouse.addLisenter(MouseEventType.ON_DOUBLE_CLICK, callback);
        mouse.doubleClick();

    }

}
