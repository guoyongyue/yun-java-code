package com.yun.code.package0000.package10;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 */
class S14最长公共前缀 {
    final static String EMPTY = "";
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return EMPTY;
        char[] alter = strs[0].toCharArray();
        int len=alter.length;
        for (int i = 1; i < strs.length; ++i) {
            String cur = strs[i];
            if (cur == null)
                return EMPTY;
            len=Math.min(len, cur.length());
            for (int j = 0; j < len && j < cur.length(); ++j) {
                if (cur.charAt(j) != alter[j]) {
                    len=j;
                    break;
                }
            }
        }
        return String.valueOf(alter, 0, len);
    }
}