package com.yun.code.util;

import java.util.Collection;
import java.util.Map;

public class IsEmptyUtil {



    public static boolean isEmpty4String(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return false;
        } else {
            for (int i = 0; i < strArr.length; i++) {
                if (strArr[i] == null || strArr[i].length() == 0) {
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean isEmpty4CollectIon(Collection... collectionArr) {
        if (collectionArr == null || collectionArr.length == 0) {
            return false;
        } else {
            for (int i = 0; i < collectionArr.length; i++) {
                if (collectionArr[i] == null || collectionArr[i].size() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isEmpty4Map(Map... mapArr) {
        if (mapArr == null || mapArr.length == 0) {
            return false;
        } else {
            for (int i = 0; i < mapArr.length; i++) {
                if (mapArr[i] == null || mapArr[i].size() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /*public boolean isEmpty4Object(Object... objArr){
        if(objArr==null || objArr.length==0){
            return false;
        }else {
            for (int i = 0; i < objArr.length; i++) {
                if(objArr[i] == null){
                    return false;
                }
            }
        }
        return true;
    }*/


    public static boolean isEmpty4Object(Object... objectArr) {
        if (objectArr == null || objectArr.length == 0) {
            return false;
        }
        for (int i = 0; i < objectArr.length; i++) {
            if(objectArr[i]==null){
                return false;
            }

            if (objectArr[i] instanceof String) {
                String str = (String) objectArr[i];
                return str.isEmpty();
            } else if (objectArr[i] instanceof Collection) {
                Collection collection = (Collection) objectArr[i];
                return collection.isEmpty();
            } else if (objectArr[i] instanceof Map) {
                Map map = (Map) objectArr[i];
                return map.isEmpty();
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        int[] intArr = new int[10];
        int[] intArr = null;
        System.out.println(IsEmptyUtil.isEmpty4Object(intArr));;
    }
}