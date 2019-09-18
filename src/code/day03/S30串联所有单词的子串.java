package code.day03;

import java.util.List;

public class S30串联所有单词的子串 {
    /**
     * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
     *
     * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
     *
     * 输入：
     *   s = "barfoothefoobarman",
     *   words = ["foo","bar"]
     * 输出：[0,9]
     */

    public static void main(String[] args) {

    }

    public List<Integer> findSubString(String s, String[] words) {
        if (s==null || s.length()==0){
            return null;
        }

        if(words==null || words.length==0){
            return null;
        }

        int wordLength=words[1].length();

        for (int i=0;i<s.length()-wordLength;i++){
            String substring = s.substring(i, i + wordLength);

            
        }

        return null;
    }
}
