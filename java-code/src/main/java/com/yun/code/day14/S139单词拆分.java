package com.yun.code.day14;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 */
public class S139单词拆分 {
    public static void main(String[] args) {
        S139单词拆分 s139单词拆分 = new S139单词拆分();
        List<String> set = new ArrayList<>();
        set.add(new String("aaaa"));
        set.add(new String("aaa"));
        boolean leetcode = s139单词拆分.wordBreak("aaaaaaa", set);
        System.out.println(leetcode);
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        //ba babd
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /*public boolean wordBreak(String s, List<String> wordDict) {
        if(s==null || s.length()==0){
            return false;
        }

        if(wordDict==null || wordDict.size()==0){
            return false;
        }
        Set<String> set = new HashSet<>(wordDict);
        int beginIndex=0;
        for (int i = 1; i <= s.length(); i++) {
            boolean contains = set.contains(s.substring(beginIndex, i));
            if(contains==true){
                beginIndex=i;
            }
        }
        if(beginIndex==s.length()){
            return true;
        }else {
            return false;
        }
    }*/



}
