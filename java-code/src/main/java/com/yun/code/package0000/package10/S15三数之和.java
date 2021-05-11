package com.yun.code.package0000.package10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 题目大意：
 * 给定一个n个元素的数组，是否存在a，b，c三个元素，使用得a+b+c=0，找出所有符合这个条件的三元组
 *
 * 注意：
 *   - 三元组中的元素必须是非递减的
 *   - 结果不能包含重复元素
 *
 */
class S15三数之和 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();

        if (nums != null && nums.length > 2) {
            // 先对数组进行排序
            Arrays.sort(nums);
            // i表示如果取第i个数作为结果
            for (int i = 0; i < nums.length - 2; ) {
                // 第二个数可能的起始位置
                int j = i + 1;
                // 第三个数可能是结束位置
                int k = nums.length - 1;

                while (j < k) {
                    // 如果找到满足条件的解
                    if (nums[j] + nums[k] == -nums[i]) {
                        // 将结果加入到结果含集中
                        List<Integer> list = new ArrayList<Integer>(3);
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        result.add(list);

                        // 移动到下一个位置。找下一组解
                        k--;
                        j++;

                        // 从左向右找第一个与之前处理的数不同的数的下标
                        while (j < k && nums[j] == nums[j - 1]) {
                            j++;
                        }
                        // 从右向左找第一个与之前处理的数不同的数的下标
                        while (j < k && nums[k] == nums[k + 1]) {
                            k--;
                        }
                    }
                    // 和大于0
                    else if (nums[j] + nums[k] > -nums[i]) {
                        k--;
                        // 从右向左找第一个与之前处理的数不同的数的下标
                        while (j < k && nums[k] == nums[k + 1]) {
                            k--;
                        }
                    }
                    // 和小于0
                    else {
                        j++;
                        // 从左向右找第一个与之前处理的数不同的数的下标
                        while (j < k && nums[j] == nums[j - 1]) {
                            j++;
                        }
                    }
                }

                // 指向下一个要处理的数
                i++;
                // 从左向右找第一个与之前处理的数不同的数的下标
                while (i < nums.length - 2 && nums[i] == nums[i - 1]) {
                    i++;
                }
            }
        }

        return result;
    }
}