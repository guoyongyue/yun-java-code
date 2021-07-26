package com.yun.code.package1400.package50;

/**
 * 给你字符串 s 和整数 k 。
 *
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 *
 * 英文中的 元音字母 为（a, e, i, o, u）。
 *
 */
public class S1458定长子串中元音的最大数目 {
    public int maxVowels(String s, int k) {
        int num=0;
        int max=0;
        for (int i = 0; i < k; i++) {
            num = isVowel(s.charAt(i))?num+1:num;
        }
        if(num==k){
            return k;
        }else {
            max=num;
        }
        for (int i = k; i < s.length(); i++) {
            num = isVowel(s.charAt(i))?num+1:num;
            num = isVowel(s.charAt(i-k))?num-1:num;
            max=Math.max(max,num);
        }
        return max;
    }
    public Boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ? Boolean.TRUE : Boolean.FALSE;
    }
}
