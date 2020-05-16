package com.yun.code.day09;

import com.yun.code.ListNode;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 */
public class S83删除排序链表中的重复元素 {
    public static void main(String[] args) {

    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode node = head;
        int tempVal = head.val;
        while (node.next != null) {
            if (tempVal == node.next.val) {
                if (node.next.next != null) {
                    node.next = node.next.next;
                } else {
                    node.next = null;
                }
            } else {
                tempVal = node.next.val;
            }
            node = node.next;
        }
        return head;
    }

}
