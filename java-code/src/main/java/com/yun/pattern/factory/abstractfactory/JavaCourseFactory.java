package com.yun.pattern.factory.abstractfactory;

/**
 * @class name: JavaCourseFactory <br/>
 * @description: <br/>
 * @date: 2020/5/23 11:58<br/>
 * @author: yun<br />
 */
public class JavaCourseFactory implements CourseFactory {

    /**
     * @param
     * @method name: createNote <br/>
     * @description:
     * @date: 2020/5/23 11:58<br/>
     * @author: yun
     */
    @Override
    public INote createNote() {
        return new JavaNote();
    }

    /**
     * @param
     * @method name: createVideo <br/>
     * @description:
     * @date: 2020/5/23 11:58<br/>
     * @author: yun
     */
    @Override
    public IVideo createVideo() {
        return new JavaVideo();
    }
}
