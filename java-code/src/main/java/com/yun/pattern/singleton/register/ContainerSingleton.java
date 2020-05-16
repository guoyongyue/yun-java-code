package com.yun.pattern.singleton.register;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContainerSingleton {
    private static Map instanceMaps = new ConcurrentHashMap<String,Object>();

    private ContainerSingleton(){

    }

    public static Object getInstance(String clazzName){
        if(!instanceMaps.containsKey(clazzName)) {
            synchronized(ContainerSingleton.class){
                try {
                    Class<?> clazz = Class.forName(clazzName);
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    Object object = constructor.newInstance();
                    instanceMaps.put(clazzName,object);
                     return object;
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return instanceMaps.get(clazzName);
    }
}
