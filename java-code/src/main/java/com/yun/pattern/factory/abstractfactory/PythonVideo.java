package com.yun.pattern.factory.abstractfactory;

/**
 * @class name: PythonVideo <br/>
 * @description: <br/>
 * @date: 2020/5/23 12:00<br/>
 * @author: yun<br />
 */
public class PythonVideo implements IVideo {

    /**
     * @param
     * @method name: record <br/>
     * @description:
     * @date: 2020/5/23 12:01<br/>
     * @author: yun
     */
    @Override
    public void record() {
        System.out.println("录制Python视频");
    }
}
