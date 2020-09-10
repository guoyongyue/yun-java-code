package com.yun.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapUtil {
    public static void print(Map map) {
        map.forEach((key, value) ->
                System.out.printf("key: %s; value: %s; ", key, value)
        );
    }

    public static void print(ConcurrentHashMap map) {
        map.forEach(2, (key, value) ->
                System.out.printf("key: %s; value: %s; ", key, value)
        );
    }

}
