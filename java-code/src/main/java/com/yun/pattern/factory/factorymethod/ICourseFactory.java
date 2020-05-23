package com.yun.pattern.factory.factorymethod;

import com.yun.pattern.factory.ICourse;

/**
 * @class name: ICourseFactory <br/>
 * @description: <br/>
 * @date: 2020/5/23 12:01<br/>
 * @author: yun<br />
 */
public interface ICourseFactory {

    /**
     * @param
     * @method name: create <br/>
     * @description:
     * @date: 2020/5/23 12:01<br/>
     * @author: yun
     */
    ICourse create();
}
