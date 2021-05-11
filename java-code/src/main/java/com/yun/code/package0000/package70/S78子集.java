package com.yun.code.package0000.package70;

import com.yun.util.InitUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 */
public class S78子集 {
    private List<List<Integer>> output = new LinkedList<>();
    private int k;

    public static void main(String[] args) {
        S78子集 S78子集 = new S78子集();
        int[] ints = InitUtil.initIntArray("1,2,3,4,5,7");
        List<List<Integer>> subsets = S78子集.subsets(ints);
        System.out.println(subsets);

    }

    public List<List<Integer>> subsets(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            this.k=i;
            backtrack(1,new LinkedList<>(),nums);
        }
        output.add(new LinkedList<>());
        return output;
    }

    public void backtrack(int n,LinkedList<Integer> curr,int[] nums){
        if(curr.size()==k){
            output.add(new LinkedList(curr));
        }
        for (int i = n; i < nums.length; i++) {
            curr.add(i);
            backtrack(i + 1, curr,nums);
            curr.removeLast();
        }
    }
}
