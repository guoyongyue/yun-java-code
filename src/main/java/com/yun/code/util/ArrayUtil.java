package com.yun.code.util;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ArrayUtil {
    public static void main(String[] args) {
        System.out.println(">>>>>>>>>>> project begin");;
    }




    //旋转二维数值从行格式变为列格式
    public static String[][] change(String[][] matrix) {
        String[][] temp = new String[matrix[0].length][matrix.length];
        int dst = matrix.length - 1;
        for (int i = 0; i < matrix.length; i++, dst--) {
            for (int j = 0; j < matrix[0].length; j++) {
                temp[j][dst] = matrix[dst][j];
            }
        }
        return temp;
    }


    //将普通的二维数据进行裁剪
    public static String[][] change2(String[][] matrix) {

        //每一块的数据
        ArrayList<String> arrayList = new ArrayList<>();
        //每一块的数量
        ArrayList<Integer> arrayLength = new ArrayList<>();
        //每一块的开始坐标
        ArrayList<Integer> arrayBeginIndex = new ArrayList<>();
        arrayBeginIndex.add(0);
        int maxLength = 0;//最大块的长度
        int maxBlockEndNum = 0;//最大块的结束坐标
        for (int i = 0; i < matrix[0].length; i++) {
            String type = matrix[1][i];
            boolean diff_block_sign = true;
            if (arrayList.size() != 0) {
                diff_block_sign = arrayList.get(0).equals(type);
            }
            if (diff_block_sign == true) {
                arrayList.add(type);
            } else {
                if (maxLength < arrayList.size()) {
                    maxLength = arrayList.size();
                    maxBlockEndNum = i;
                }
                arrayLength.add(arrayList.size());
                arrayList.clear();
                arrayList.add(type);
                arrayBeginIndex.add(i);
            }
        }
        arrayLength.add(arrayList.size());
        String[][] temp = new String[arrayLength.size() + 1][];
        //设置x轴
        System.out.println(maxBlockEndNum + " " + maxLength);
        temp[0] = ArrayUtil.arraySub(matrix[0], maxBlockEndNum - maxLength, maxBlockEndNum);
//        print(temp[0]);
        for (int i = 0; i < arrayLength.size(); i++) {
            temp[i + 1] = ArrayUtil.arraySub(matrix[2], arrayBeginIndex.get(i), arrayBeginIndex.get(i) + arrayLength.get(i));
        }
        return temp;
    }


    //截取数组的一部分
    public static String[] arraySub(String[] data, int start, int end) {
        String[] temp = new String[end - start];
        int j = 0;
        for (int i = start; i < end; i++) {
            temp[j] = data[i];
            j++;
        }
        return temp;
    }


    public static void print(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            print(matrix[i]);
            System.out.println();
        }
    }

    public static void print(String[] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i] + " ");
        }
    }

    public static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            print(matrix[i]);
            System.out.println();
        }
    }

    public static void print(int[] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i] + " ");
        }
        System.out.println();
    }


    public static void print(List<Object> list) {
        list.forEach(key -> System.out.println(key));
    }

    public static void print(LinkedList<Integer> list) {
        list.forEach(key -> System.out.println(key));
    }


    public static void print(Map<String, String> map) {
        map.forEach((key, value) -> System.out.printf("key : %s; value : %s; thread: %s\n", key, value, Thread.currentThread().getName()));
    }


    public static int[] initArray(String array) {
        String[] splitStr = array.split(",");
        int[] splitInt= new int[splitStr.length];
        for (int i = 0; i < splitStr.length; i++) {
            splitInt[i]=Integer.valueOf(splitStr[i]);
        }
        return splitInt;
    }

    public static int[][] initArray(String... arrays) {
        int x_length = arrays[0].length();
        int y_length = arrays.length;
        int[][] split = new int[y_length][x_length];
        IntStream.range(0,y_length).forEach(i -> {
            int[] ints = initArray(arrays[i]);
            split[i] = ints;
        });
        return split;
    }

}
