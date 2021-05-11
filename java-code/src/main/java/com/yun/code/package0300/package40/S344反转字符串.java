package com.yun.code.package0300.package40;


import com.yun.util.InitUtil;

/**
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * <p>
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * <p>
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 */

/*e l l o h
l l o e h
l o l e h
o l l e h*/

public class S344反转字符串 {
    public static void main(String[] args) {
        char[] hellos = InitUtil.initCharArray("hello");
        System.out.println(reverseString2(hellos));
    }

    public static char[] reverseString(char[] s) {
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s.length - i - 1; j++) {
                char temp = s[j];
                s[j] = s[j + 1];
                s[j + 1] = temp;
            }
        }
        return s;
    }

    public static char[] reverseString2(char[] s) {
        int left=0,right=s.length-1;
        while (left<right){
            char temp = s[right];
            s[right] = s[left];
            s[left] = temp;
            left++;
            right--;
        }
        return s;
    }
}
