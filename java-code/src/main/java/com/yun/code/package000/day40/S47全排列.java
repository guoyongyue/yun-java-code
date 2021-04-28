package com.yun.code.package000.day40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 */
public class S47全排列 {
    public static void main(String[] args) {

    }
    List<List<Integer>>list;
    int []nums;
    public List<List<Integer>> permuteUnique(int[] nums) {
        this.nums=nums;
        list=new ArrayList<>();
        //切记必须要先排序啊！！！！！！这样只有相邻的才可能相等，才可以判断去除！！！！！
        Arrays.sort(nums);
        List<Integer>ll=new ArrayList<>();
        boolean []flag=new boolean[nums.length];
        dfs(ll,flag,0);
        return list;
    }
    public void dfs(List<Integer>ll,boolean[]flag,int length){
        if(length==nums.length)
        {
            list.add(new ArrayList<>(ll));
            return ;
        }
        for(int i=0;i<nums.length;i++)
        {
            //这个位置用过了
            if(flag[i])
                continue;
            //i-1和i的值相等，且i-1没被用过（之后可能会被用就产生重复）flag【i-1】=false是取相等的数中最左边的那个数当ll的第一个数，而flag【i-1】=true就是取相等的数中最右边的那个数当ll的第一个数，也就是说分别取第一行和第二行。
            if(i>0&&nums[i-1]==nums[i]&&flag[i-1]==false)
            {
                continue;
            }
            ll.add(nums[i]);
            flag[i]=true;
            dfs(ll,flag,length+1);
            ll.remove(ll.size()-1);
            flag[i]=false;
        }
    }

}
