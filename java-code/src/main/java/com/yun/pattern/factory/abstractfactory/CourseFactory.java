package com.yun.pattern.factory.abstractfactory;

/**
 * @class name: CourseFactory <br/>
 * @description: <br/>
 * @date: 2020/5/23 11:55<br/>
 * @author: yun<br />
 */
public interface CourseFactory {

    /**
     * @param
     * @method name: createNote <br/>
     * @description:
     * @date: 2020/5/23 11:55<br/>
     * @author: yun
     */
    INote createNote();

    /**
     * @param
     * @method name: createVideo <br/>
     * @description:
     * @date: 2020/5/23 11:55<br/>
     * @author: yun
     */
    IVideo createVideo();
}
