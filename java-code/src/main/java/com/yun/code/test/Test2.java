package com.yun.code.test;

import java.util.Scanner;

public class Test2 {

    public static String StringChallenge(String str) {
        // code goes here
        String value="";
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i)==' '){
                value+=" ";
            }else{
                value+=(str.charAt(i)+0);
            }
        }
        return value;
    }

    public static void main (String[] args) {
        // keep this function call here
        System.out.print(StringChallenge("abc **"));
    }

}
