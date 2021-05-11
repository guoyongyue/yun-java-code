package com.yun.code.package0100.package30;

import com.yun.util.InitUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 */
public class S137只出现一次的数字 {
    public static void main(String[] args) {
        int i = singleNumber(InitUtil.initIntArray("2,2,3,2"));
        System.out.println(i);
    }
    public static int singleNumber(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        for(Map.Entry<Integer, Integer> entrie:map.entrySet()){
            Integer key = entrie.getKey();
            if(entrie.getValue()==1){
                return Integer.valueOf(key);
            }
        }
        return 0;
    }
}
