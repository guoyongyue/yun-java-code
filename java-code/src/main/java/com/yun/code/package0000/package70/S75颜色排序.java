package com.yun.code.package0000.package70;

import com.yun.util.InitUtil;
import com.yun.util.PrintUtil;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 */
public class S75颜色排序 {
    public static void main(String[] args) {
        int[] ints = InitUtil.initIntArray("0,2,2,1,0,1");
        PrintUtil.print(ints);
        System.out.println("------------");
        S75颜色排序.sortColors(ints);
//        ArrayUtil.print(ints);
    }

    public static void sortColors(int[] nums) {
        //来分别追踪0的最右边界
        int p0 = 0;

        int curr = 0;
        //2的最左边界
        int p2 = nums.length - 1;
        int tmp;
        while (curr <= p2) {
            if (nums[curr] == 0) {
                // 交换第 p0个和第curr个元素
                // i++，j++
                tmp = nums[p0];
                nums[p0++] = nums[curr];
                nums[curr++] = tmp;
            }
            else if (nums[curr] == 2) {
                // 交换第k个和第curr个元素
                // p2--
                tmp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2--] = tmp;
            }
            else{
                curr++;
            }
            PrintUtil.print(nums);
        }


    }
}
