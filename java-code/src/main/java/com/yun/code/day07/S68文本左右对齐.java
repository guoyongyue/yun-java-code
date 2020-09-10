package com.yun.code.day07;

import com.yun.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 *
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 *
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 *
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 */
public class S68文本左右对齐 {
    public static void main(String[] args) {

//        int[] ints = ArrayUtil.initArray("1,2,3,4,5");
        int[][] ints = ArrayUtil.initArray("1,2,3,4,6","1,2,3,4,5");
        ArrayUtil.print(ints);

       /* String[]  words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;

        List<String> list = fullJustify(words, maxWidth);
        ArrayUtil.print(list);*/
    }
    public static List<String> fullJustify(String[] words, int maxWidth) {
        String temp ="";
        List<String> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            int newStrLength = temp.length()+words[i].length()+1;
            if(newStrLength>maxWidth){
                list.add(temp);
                temp=words[i];
            }else {
                if(temp==null || temp.length()==0){
                    temp=words[i];
                }else {
                    temp=temp+" "+words[i];
                }

            }
        }
        if(temp!=null && temp.length()>0){
            list.add(temp);
        }
        return list;
    }
}
