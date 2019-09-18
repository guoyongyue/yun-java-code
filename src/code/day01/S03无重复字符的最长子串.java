package code.day01;

import java.util.HashSet;
import java.util.Set;

public class S03无重复字符的最长子串 {
    /**
     * 给定一个字符串，找出不含有重复字符的最长子串的长度。
     * 示例：
     * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
     * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
     * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串
     * @return
     */
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("ontnull"));

    }
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<Character>();
        int ans = 0, i = 0, j = 0;
        //利用set结构，不重复add，重复remove
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
            System.out.println(i+" "+j+" "+set);
        }
        return ans;
    }
}