package com.yun.pattern.factory.abstractfactory;

/**
 * @class name: JavaVideo <br/>
 * @description: <br/>
 * @date: 2020/5/23 11:59<br/>
 * @author: yun<br />
 */
public class JavaVideo implements IVideo {

    /**
     * @param
     * @method name: record <br/>
     * @description:
     * @date: 2020/5/23 11:59<br/>
     * @author: yun
     */
    @Override
    public void record() {
        System.out.println("录制Java视频");
    }
}
