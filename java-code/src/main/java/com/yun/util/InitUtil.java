package com.yun.util;


import java.util.stream.IntStream;

public class InitUtil {
    public static void main(String[] args) {
        System.out.println(">>>>>>>>>>> project begin");;
    }


    public static int[] initIntArray(String array) {
        String[] splitStr = array.split(",");
        int[] splitInt= new int[splitStr.length];
        for (int i = 0; i < splitStr.length; i++) {
            splitInt[i]=Integer.valueOf(splitStr[i]);
        }
        return splitInt;
    }


    public static int[][] initIntArray(String... arrays) {
        int x_length = arrays[0].length();
        int y_length = arrays.length;
        int[][] split = new int[y_length][x_length];
        IntStream.range(0,y_length).forEach(i -> {
            int[] ints = initIntArray(arrays[i]);
            split[i] = ints;
        });
        return split;
    }

    public static char[] initCharArray(String array) {
        char[] c = new char[array.length()];
        for (int i = 0; i < array.length(); i++) {
            c[i]=array.charAt(i);
        }
        return c;
    }

}
