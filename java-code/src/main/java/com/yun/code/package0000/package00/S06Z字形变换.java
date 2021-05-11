package com.yun.code.package0000.package00;

import java.util.ArrayList;
import java.util.List;

/**
 * 将字符串 "PAYPALISHIRING" 以Z字形排列成给定的行数：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后从左往右，逐行读取字符："PAHNAPLSIIGYIR"
 *
 * 实现一个将字符串进行指定行数变换的函数:
 *
 * string convert(string s, int numRows);
 * 示例 1:
 *
 * 输入: s = "PAYPALISHIRING", numRows = 3
 * 输出: "PAHNAPLSIIGYIR"
 * 示例 2:
 *
 * 输入: s = "PAYPALISHIRING", numRows = 4
 * 输出: "PINALSIGYAHRPI"
 * 解释:
 *
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 */
class S06Z字形变换 {
    public String convert(String s, int numRows) {
         if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }
}