package com.yun.code.package0000.package20;

public class S28实现strStr {
    /**
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
     * 如果不存在，则返回  -1。
     * @param args
     */

    public static void main(String[] args) {
        System.out.println(strStr("mississippi","issipi"));
    }

    public static int strStr(String haystack, String needle) {


        if(haystack==null || haystack.length()==0){
            return -1;
        }

        if(needle==null || needle.length()==0){
            return 0;
        }

        if(haystack.length()<needle.length()){
            return -1;
        }


        for (int i = 0; i < haystack.length(); i++) {
            if(haystack.charAt(i)==needle.charAt(0)){
                for (int j = 0; j < needle.length(); j++) {

                    if(haystack.charAt(i+j)!=needle.charAt(j)){
                        break;
                    }

                    if(needle.length()>(haystack.length()-i)){
                        break;
                    }

                    if(j==(needle.length()-1)){
                        return i;
                    }
                }
            }
        }

        return -1;
    }

}
