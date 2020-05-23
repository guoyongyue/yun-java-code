package com.yun.pattern.factory.simplefactory;

import com.yun.pattern.factory.ICourse;

/**
 * @class name: CourseFactory <br/>
 * @description: <br/>
 * @date: 2020/5/23 12:03<br/>
 * @author: yun<br />
 */
public class CourseFactory {

    /**
     * @param
     * @method name: create <br/>
     * @description:
     * @date: 2020/5/23 12:03<br/>
     * @author: yun
     */
    public ICourse create(Class<? extends ICourse> clazz) {
        if (null != clazz) {
            try {
                return clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
