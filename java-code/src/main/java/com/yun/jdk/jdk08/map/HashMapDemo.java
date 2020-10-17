package com.yun.jdk.jdk08.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * @author: yun<\br>
 * @description: <\br>
 * @date:  2020/5/11 9:45<\br>
*/
public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.putIfAbsent("foo", "bar");
        hashMap.putIfAbsent("han", "solo");
        hashMap.putIfAbsent("r2", "d2");
        hashMap.putIfAbsent("c3", "p0");

        System.out.println("print begin========>");
        HashMapDemo.print(hashMap);

        System.out.println("merge begin========>");
        HashMapDemo.merge(hashMap);
    }

    private static void print(HashMap<String,String> hashMap){

        hashMap.forEach((key,value)-> System.out.println(key+":"+value));
        for(Map.Entry<String, String> entry : hashMap.entrySet()){
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();
            //System.out.println(mapKey+":"+mapValue);
        }

        for(String value : hashMap.values()){
            //System.out.println(value);
        }

        Iterator<Map.Entry<String, String>> entries = hashMap.entrySet().iterator();
        while(entries.hasNext()){
            Map.Entry<String, String> entry = entries.next();
            String key = entry.getKey();
            String value = entry.getValue();
            //System.out.println(key+":"+value);
        }

        for(String key : hashMap.keySet()){
            String value = hashMap.get(key);
            //System.out.println(key+":"+value);
        }
    }

    private static void merge(HashMap hashMap){
        hashMap.merge("c3", "p0", (value, newValue) -> "new "+ value);
        System.out.println("c3:"+hashMap.get("c3"));

    }
}
