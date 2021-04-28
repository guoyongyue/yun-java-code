package com.yun.code.package01.day09;

import com.yun.code.ListNode;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 */
public class S82删除排序链表中的重复元素II {
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
                node = deleteDuplicates(node, tempVal);
            } else {
                tempVal = node.next.val;
            }
            node = node.next;
        }
        return head;
    }

    public ListNode deleteDuplicates(ListNode head,int value) {
        while (head.next != null) {
           if(value==head.next.val){
               head = head.next;
           }else {
               head = head.next;
               break;
           }
        }
        return head;
    }

}
