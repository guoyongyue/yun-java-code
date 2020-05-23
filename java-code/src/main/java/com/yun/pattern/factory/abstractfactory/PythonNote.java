package com.yun.pattern.factory.abstractfactory;

/**
 * @class name: PythonNote <br/>
 * @description: <br/>
 * @date: 2020/5/23 12:00<br/>
 * @author: yun<br />
 */
public class PythonNote implements INote {

    /**
     * @param
     * @method name: edit <br/>
     * @description:
     * @date: 2020/5/23 12:00<br/>
     * @author: yun
     */
    @Override
    public void edit() {
        System.out.println("编写Python笔记");
    }
}
