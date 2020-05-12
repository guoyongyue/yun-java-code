package com.yun.code.day08;

import com.yun.code.util.ArrayUtil;

/**
 *给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 */
public class S80删除排序数组中的重复项 {
    public static void main(String[] args) {
        int[] ints = ArrayUtil.initArray("1,2,2,2,3,5,6");
        S80删除排序数组中的重复项 S80删除排序数组中的重复项 =new S80删除排序数组中的重复项();
        S80删除排序数组中的重复项.removeDuplicates(ints);
        ArrayUtil.print(ints);
    }

    public int removeDuplicates(int[] nums) {

        int i = 1, count = 1, length = nums.length;
        while (i < length) {
            if (nums[i] == nums[i - 1]) {
                count++;
                if (count > 2) {
                    this.remElement(nums, i);
                    i--;
                    length--;
                }
            } else {
                count = 1;
            }
            i++;
        }
        return length;
    }

    public int[] remElement(int[] arr, int index) {
        for (int i = index + 1; i < arr.length; i++) {
            arr[i - 1] = arr[i];
        }
        return arr;
    }



}
