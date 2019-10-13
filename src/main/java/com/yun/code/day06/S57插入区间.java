package com.yun.code.day06;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 */
public class S57插入区间 {
    public static void main(String[] args) {

    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int i = 0;
        while (i < intervals.length && newInterval[0] > intervals[i][1]) {
            res.add(intervals[i]);
            i++;
        }
        int[] tmp = new int[]{newInterval[0], newInterval[1]};
        while (i < intervals.length && newInterval[1] >= intervals[i][0]) {
            tmp[0] = Math.min(tmp[0], intervals[i][0]);
            tmp[1] = Math.max(tmp[1], intervals[i][1]);
            i++;
        }
        res.add(tmp);
        while (i < intervals.length) {
            res.add(intervals[i]);
            i++;
        }
        return res.toArray(new int[0][]);
    }

}
