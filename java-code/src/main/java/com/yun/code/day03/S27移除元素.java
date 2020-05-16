package com.yun.code.day03;

public class S27移除元素 {
    public static void main(String[] args) {
        int[] a={2,1,2,3,3,4};
        System.out.println(removeElement(a,3));
    }
    public static int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}
