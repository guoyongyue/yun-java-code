package com.yun.code.day10;

/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 */
public class S91解码方法 {
    public static void main(String[] args) {
        String s = "12321";
        S91解码方法 s91解码方法 =new S91解码方法();
        System.out.println(s91解码方法.numDecoding("1"));
        System.out.println(s91解码方法.numDecoding("12"));
        System.out.println(s91解码方法.numDecoding("28"));
        System.out.println(s91解码方法.numDecoding("299"));
        System.out.println(s91解码方法.numDecoding("1234"));
        // 1 2 3 4
        // 12 3  4
        //
    }

    public int numDecoding(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        if (s.length() == 1) {
            return 1;
        }
        int[] temp = new int[s.length()];
        temp[0] = 1;
        String substring = s.substring(0, 2);
        Integer integer = Integer.valueOf(substring);
        temp[1] = ((integer > 9 && integer < 27) ? 2 : 1);
        test(temp,s);

        return temp[temp.length-1];

    }

    private void test(int[] ints, String s) {
        for (int i = 2; i < s.length(); i++) {
            String substring = s.substring(i - 2, i);
            Integer integer = Integer.valueOf(substring);
            ints[i] = ((integer > 9 && integer < 27) ? ints[i - 1] + 1 : ints[i - 1]);
        }
    }

}
