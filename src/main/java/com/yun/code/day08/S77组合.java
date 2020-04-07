package com.yun.code.day08;

import com.yun.code.util.ArrayUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 */
public class S77组合 {
    List<List<Integer>> output = new LinkedList();
    int n;
    int k;

    public static void main(String[] args) {

        S77组合 s77组合 = new S77组合();
        s77组合.combine(6, 3);
    }

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        backtrack(1, new LinkedList<Integer>());
        return output;
    }


    public void backtrack(int first, LinkedList<Integer> curr) {
        if (curr.size() == k){
            output.add(new LinkedList(curr));
        }

        for (int i = first; i < n + 1; ++i) {
            // add i into the current combination
            curr.add(i);
            // use next integers to complete the combination
            backtrack(i + 1, curr);
            // backtrack
            curr.removeLast();
        }
    }
}
