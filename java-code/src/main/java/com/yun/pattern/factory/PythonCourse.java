package com.yun.pattern.factory;

/**
 * @class name: PythonCourse <br/>
 * @description: <br/>
 * @date: 2020/5/23 11:54<br/>
 * @author: yun<br />
 */
public class PythonCourse implements ICourse {


    /**
     * @param
     * @method name: record <br/>
     * @description:
     * @date: 2020/5/23 11:54<br/>
     * @author: yun
     */
    @Override
    public void record() {
        System.out.println("录制python课程");
    }
}
