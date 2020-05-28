package com.yun.code;
/**
 * @author: yun<\br>
 * @description: <\br>
 * @date:  2020/5/26 9:21<\br>
*/
public class ListNode implements Comparable<ListNode> {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int x) {
        val = x;
    }

    @Override
    public int compareTo(ListNode o) {
        return 0;
    }
}