package com.yun.code.package000.day80;

import com.yun.code.ListNode;

/**
 * @author: yun<\ br>
 * @description: 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 * @date: 2020/5/26 9:19<\br>
 */
public class S86分隔链表 {
    public static void main(String[] args) {

    }

    public ListNode partition(ListNode head, int x) {

        ListNode beforeHead = new ListNode(0);
        ListNode afterHead = new ListNode(0);

        ListNode before = beforeHead;
        ListNode after = afterHead;
        while (head != null) {
            if (head.val >= x) {
                after.next = head;
                after = after.next;
            } else {
                before.next = head;
                before = before.next;
            }

            head = head.next;
        }
        after.next = null;

        before.next = afterHead.next;

        return beforeHead.next;
    }
}
