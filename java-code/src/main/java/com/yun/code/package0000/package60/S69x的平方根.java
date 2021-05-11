package com.yun.code.package0000.package60;

/**
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 */
public class S69x的平方根 {
    public static void main(String[] args) {
        System.out.println(mySqrt(1));;
    }
    public static int mySqrt(int x) {
        if(x==0){
            return 0;
        }
        int left = 1;
        int right = x/2+1 ;
        for (int i=left;i<right;i++){

            if( Math.pow((left+right)/2,2)<=x ){
                left=(left+right)/2;
            }else {
                right=(left+right)/2;
            }
        }
        return left;
    }
}
