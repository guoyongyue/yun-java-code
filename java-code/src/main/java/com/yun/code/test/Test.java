package com.yun.code.test;

/**
 * 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
 */
public class Test {
    public static void main(String[] args) {

    }

    public int[] swapIntArr(int[] numbers){
        numbers[0]=numbers[0]+numbers[1];
        numbers[1]=numbers[0]-numbers[1];
        numbers[0]=numbers[0]-numbers[1];
       return numbers;
    }
}
