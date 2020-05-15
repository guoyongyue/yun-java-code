package com.gupao.vip.pattern.delegate.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qingbowu on 2019/3/15.
 */
public class Leader {

    //leader要预先知道每个员工的特长，特征，然后分发任务
    private Map<String,IEmployee> register = new HashMap<String,IEmployee>();
    public Leader(){
        register.put("sign",new EmployeeA());
        register.put("js",new EmployeeB());
    }

    public void doSomething(String command){
        register.get(command).doSomeThing(command);
    }
}
