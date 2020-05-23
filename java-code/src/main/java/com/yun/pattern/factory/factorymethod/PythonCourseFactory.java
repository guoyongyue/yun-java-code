package com.yun.pattern.factory.factorymethod;

import com.yun.pattern.factory.ICourse;
import com.yun.pattern.factory.PythonCourse;

/**
 * @class name: PythonCourseFactory <br/>
 * @description: <br/>
 * @date: 2020/5/23 12:02<br/>
 * @author: yun<br />
 */
public class PythonCourseFactory implements ICourseFactory {


    /**
     * @param
     * @method name: create <br/>
     * @description:
     * @date: 2020/5/23 12:02<br/>
     * @author: yun
     */
    @Override
    public ICourse create() {
        return new PythonCourse();
    }
}
