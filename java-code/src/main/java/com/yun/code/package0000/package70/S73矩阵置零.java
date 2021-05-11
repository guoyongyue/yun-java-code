package com.yun.code.package0000.package70;

import com.yun.util.InitUtil;
import com.yun.util.PrintUtil;

import java.util.HashSet;
import java.util.Set;

/**
 *
 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 */
public class S73矩阵置零 {
    public static void main(String[] args) {
        int[][] ints = InitUtil.initIntArray("0,1,2,0","3,4,5,2","1,3,1,5");
        PrintUtil.print(ints);
    }

        public void setZeroes(int[][] matrix) {
            int R = matrix.length;
            int C = matrix[0].length;
            Set<Integer> rows = new HashSet<Integer>();
            Set<Integer> cols = new HashSet<Integer>();

            // Essentially, we mark the rows and columns that are to be made zero
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (matrix[i][j] == 0) {
                        rows.add(i);
                        cols.add(j);
                    }
                }
            }

            // Iterate over the array once again and using the rows and cols sets, update the elements.
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (rows.contains(i) || cols.contains(j)) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

}
