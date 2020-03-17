package com.yun.code.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArrayUtil {



    //掩码姓名
    public static String maskName(String name) {
        if(name==null || name.length() < 2){
            return null;
        }
        if(name.length()==2){
            name=name.substring(0,1)+"*";
        }else {
            name=name.replaceAll(name.substring(1,name.length()-1),"*");
        }
        return name;
    }

    //掩码手机号
    public static String maskPhone(String tel) {
        if(tel==null || tel.length() < 11){
            return null;
        }
        tel = tel.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return tel;
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
        int maxLength=0;//最大块的长度
        int maxBlockEndNum=0;//最大块的结束坐标
        for (int i = 0; i < matrix[0].length; i++) {
            String type = matrix[1][i];
            boolean diff_block_sign = true;
            if(arrayList.size()!=0 ){
                diff_block_sign = arrayList.get(0).equals(type);
            }
            if(diff_block_sign==true){
                arrayList.add(type);
            }else {
                if(maxLength<arrayList.size()){
                    maxLength=arrayList.size();
                    maxBlockEndNum=i;
                }
                arrayLength.add(arrayList.size());
                arrayList.clear();
                arrayList.add(type);
                arrayBeginIndex.add(i);
            }
        }
        arrayLength.add(arrayList.size());
        String[][] temp = new String[arrayLength.size()+1][];
        //设置x轴
        System.out.println(maxBlockEndNum+" "+ maxLength);
        temp[0]= ArrayUtil.arraySub(matrix[0],maxBlockEndNum-maxLength,maxBlockEndNum);
//        print(temp[0]);
        for (int i = 0; i < arrayLength.size(); i++) {
            temp[i+1] = ArrayUtil.arraySub(matrix[2],arrayBeginIndex.get(i),arrayBeginIndex.get(i)+arrayLength.get(i));
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
            System.out.print(matrix[i]+" ");
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
            System.out.print(matrix[i]+" ");
        }
    }


    public static void print(List<String> list) {
        list.forEach(key -> System.out.println(key));
    }

    public static void print(Map<String,String> map) {
        map.forEach((key,value) -> System.out.printf("key : %s; value : %s; thread: %s\n",key, value, Thread.currentThread().getName()));
    }



    public static void main(String[] args) {
        String[][] array = new String[5][12];
        array[0][0]="02-01";array[0][1]="02-02";array[0][2]="02-03";array[0][3]="02-04";array[0][4]="02-01";array[0][5]="02-02";array[0][6]="02-03";array[0][7]="02-04";array[0][8]="02-05";array[0][9]="02-01";array[0][10]="02-02";array[0][11]="02-03";
        array[1][0]="1";array[1][1]="1";array[1][2]="1";array[1][3]="1";array[1][4]="2";array[1][5]="2";array[1][6]="2";array[1][7]="2";array[1][8]="2";array[1][9]="3";array[1][10]="3";array[1][11]="3";
        array[2][0]="1";array[2][1]="2";array[2][2]="3";array[2][3]="4";array[2][4]="3";array[2][5]="4";array[2][6]="2";array[2][7]="14";array[2][8]="21";array[2][9]="11";array[2][10]="21";array[2][11]="11";
        array[3][0]="1";array[3][1]="2";array[3][2]="3";array[3][3]="4";array[3][4]="3";array[3][5]="4";array[3][6]="2";array[3][7]="14";array[3][8]="21";array[3][9]="11";array[3][10]="21";array[3][11]="11";

        String[][] strings = change2(array);
//        print(strings);

        String[][] array2 = new String[17][3];
        array2[0][0]="01";array2[0][1]="02-02";array2[0][2]="12";
        array2[1][0]="02";array2[1][1]="02-02";array2[1][2]="13";
        array2[2][0]="03";array2[2][1]="02-02";array2[2][2]="12";
        array2[3][0]="04";array2[3][1]="02-02";array2[3][2]="15";
        array2[4][0]="05";array2[4][1]="02-02";array2[4][2]="12";
        array2[5][0]="06";array2[5][1]="02-02";array2[5][2]="18";
        array2[6][0]="07";array2[6][1]="02-02";array2[6][2]="14";
        array2[7][0]="01";array2[7][1]="02-03";array2[7][2]="13";
        array2[8][0]="02";array2[8][1]="02-03";array2[8][2]="12";
        array2[9][0]="03";array2[9][1]="02-03";array2[9][2]="14";
        array2[10][0]="04";array2[10][1]="02-03";array2[10][2]="15";
        array2[11][0]="05";array2[11][1]="02-03";array2[11][2]="16";
        array2[12][0]="01";array2[12][1]="02-04";array2[12][2]="18";
        array2[13][0]="02";array2[13][1]="02-04";array2[13][2]="17";
        array2[14][0]="03";array2[14][1]="02-04";array2[14][2]="14";
        array2[15][0]="04";array2[15][1]="02-04";array2[15][2]="10";
        array2[16][0]="05";array2[16][1]="02-04";array2[16][2]="11";
        String[][] change = change(array2);
        print(change);

        String[][] strings1 = change2(change);
        System.out.println("----");
        print(strings1);
    }
}
