package com.yun.code.test02;

import java.util.Scanner;

/**
 * 有一排硬币，一共N枚，初始时都是正面朝上。接下来会有很多次操作，每次会将其中一段连续的硬币翻一面。求最后硬币的结果。
 * 输入描述:
 * 每个测试输入包含1个测试用例
 * 第一行输入两个数字N，M分别表示硬币的数量和操作的数量。其中0<N<=10000，0<M<=1000。
 * 接下来有M行，每行两个数字A, B分别表示本次操作会将第A枚硬币到第B枚硬币（包括这两枚）翻一面，其中0<A<=B<=N。
 * 输出描述:
 * 连续输出这N枚硬币的最终结果，其中0表示正面朝上，1表示反面朝上。
 * 示例1
 * 输入
 * 5 2
 * 1 2
 * 2 4
 * 输出
 * 10110
 */
public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nAndM = sc.nextLine();
        int n,m,a,b;
        String[] split = nAndM.split(" ");

        if(split !=null && split.length==2){
            n=Integer.valueOf(split[0]);
            m=Integer.valueOf(split[1]);
        }else {
            return;
        }

        String[] print =new String[n];
        int count=0;
        while (count<m){
            count++;
            String temp = sc.nextLine();
            String[] aAndB = temp.split(" ");
            if(aAndB !=null && aAndB.length==2){
                a=Integer.valueOf(aAndB[0]);
                b=Integer.valueOf(aAndB[1]);
                for(int i=(a-1);i<b;i++){
                    if(!"1".equals(print[i])){
                        print[i]="1";
                    }else {
                        print[i]="0";
                    }
                }
            }else{
                return;
            }
        }

        for (int i = 0; i < print.length; i++) {
            if(print[i]==null){
                System.out.print(0);
            }else {
                System.out.print(print[i]);
            }

        }
    }
}
