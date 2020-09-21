package com.yun.code.day13;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 *
 *  
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 */
public class S120三角形最小路径和 {
    public static void main(String[] args) {
        //[[2],[3,4],[6,5,7],[4,1,8,3]]
        S120三角形最小路径和 s120三角形最小路径和 = new S120三角形最小路径和();
        List<List<Integer>> lists= new ArrayList<List<Integer>>();
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(2);
        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(3);
        list2.add(4);
        List<Integer> list3 = new ArrayList<Integer>();
        list3.add(6);
        list3.add(5);
        list3.add(7);
        List<Integer> list4 = new ArrayList<Integer>();
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        lists.add(list4);
        int i = s120三角形最小路径和.minimumTotal(lists);
        System.out.println(i);
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size()==1){
            return triangle.get(0).get(0);
        }
        int minValue=0;
        for (int i = 0; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if(i==0){
                    continue;
                }else {
                    int value=0;
                    if(j==0){
                        value=triangle.get(i-1).get(0)+triangle.get(i).get(j);
                    }else if(j==triangle.get(i).size()-1){
                        value=triangle.get(i-1).get(j-1)+triangle.get(i).get(j);
                    }else {
                        value=Math.min(triangle.get(i-1).get(j-1),triangle.get(i-1).get(j))+triangle.get(i).get(j);
                    }
                    if((i==triangle.size()-1) && j==0){
                        minValue=value;
                    }
                    if(i==triangle.size()-1){
                        minValue=Math.min(minValue,value);
                    }
                    triangle.get(i).set(j,value);
                }
            }
        }
        return minValue;
    }
}
