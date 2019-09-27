package com.yun.code.day05;

import java.util.Arrays;

/**
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 *
 * 示例 1:
 *
 * 输入: [1,2,0]
 * 4 3 9 2 8 7 0
 * 输出: 3
 */
public class S41 {
    public static void main(String[] args) {

    }

    public int firstMissingPositive(int[] nums) {
        int min=1;
        Arrays.sort(nums);
        for (int temp:nums) {
            if(temp>0 && temp==min){
                min+=1;
            }
        }
        return min;
    }
}
