package com.yun.code.day02;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class S17电话号码的字母组合 {
    public List<String> letterCombinations(String digits) {
        //把table上的数字对应的字母列出来，当输入为2是，digits[2]就是2所对应的"abc"
        String[] table = new String[] 
                             {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> list = new ArrayList<String>();
        //index从0开始，即digits的第一个数字
        letterCombinations(list,"",digits,0,table);
        return list;
    }
    
    private void letterCombinations (List<String> list, String digits, 
                                    String curr, int index,String[] table) {
        //最后一层退出条件
        if (index == digits.length()) {
            if(curr.length() != 0) list.add(curr);
            return;
        }
        
        //找到数字对应的字符串
        String temp = table[digits.charAt(index) - '0'];
        for (int i = 0; i < temp.length(); i++) {
            //每次循环把不同字符串加到当前curr之后
            String next = curr + temp.charAt(i);
            //进入下一层
            letterCombinations(list,digits,next,index+1,table);
        }
    }
}