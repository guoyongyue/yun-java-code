package com.yun.code.package0300.package90;

/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 进阶：
 *
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 * aaacabc
 * aac
 */
public class S392判断子序列 {
    public static void main(String[] args) {
        boolean subsequence2 = new S392判断子序列().isSubsequence2("abc", "aababdcd");
        System.out.println(subsequence2);
    }
    public boolean isSubsequence(String s, String t) {
        return t.indexOf(s)>-1?Boolean.TRUE:Boolean.FALSE;
    }

    public boolean isSubsequence2(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }
}
