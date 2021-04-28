package com.yun.code.package000.day80;
/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */


/**
 * @author: yun<\ br>
 * @description: <\br>
 * @date: 2020/5/22 9:07<\br>
 */
public class S84柱状图中最大的矩形 {
    /**
     * @param heights
     * @return <\br>
     * @author: yun<\ br>
     * @description: <\br>
     * @date: 2020/5/22 9:11<\br>
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int left;
        int right;
        if ((heights.length % 2) == 0) {
            left = heights.length/2-1;
            right = heights.length/2;
        }else {
            left = heights.length/2-1;
            right = heights.length/2-1;
        }

        int minHighValue=0;
        int maxArea=0;
        while (left >= 0 || right <= heights.length) {
            if(minHighValue==0){
                minHighValue=Math.min(heights[left],heights[right]);
            }else {
                int tempminHighValue=Math.min(heights[left],heights[right]);
                minHighValue=Math.min(tempminHighValue,minHighValue);
            }

            maxArea=Math.max((right-left+1)*minHighValue,maxArea);

            if(left>0 && heights[left-1]>heights[right+1]){
                left--;
            }else if(right<heights.length && heights[left-1]<=heights[right+1]){
                right++;
            }else {
                return maxArea;
            }
        }
        return maxArea;
    }
}
