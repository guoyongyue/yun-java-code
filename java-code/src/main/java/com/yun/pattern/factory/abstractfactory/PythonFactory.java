package com.yun.pattern.factory.abstractfactory;

/**
 * @class name: PythonFactory <br/>
 * @description: <br/>
 * @date: 2020/5/23 11:59<br/>
 * @author: yun<br />
 */
public class PythonFactory implements CourseFactory {

    /**
     * @param
     * @method name: createNote <br/>
     * @description:
     * @date: 2020/5/23 11:59<br/>
     * @author: yun
     */
    @Override
    public INote createNote() {
        return new PythonNote();
    }

    /**
     * @param
     * @method name: createVideo <br/>
     * @description:
     * @date: 2020/5/23 12:00<br/>
     * @author: yun
     */
    @Override
    public IVideo createVideo() {
        return new PythonVideo();
    }
}
