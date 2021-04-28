package com.yun.code.package000.day20;


public class S21合并两个有序链表 {

    public static void main(String[] args) {
//        mergeTwoLists();
/*
        ListNode listNode01 = new ListNode(1);
        ListNode listNode02 = new ListNode(2);
        ListNode listNode03 = new ListNode(3);

        listNode01.next=listNode02;

        listNode03 = listNode01;

        System.out.println(listNode01.next.val);
        System.out.println(listNode03.next.val);*/


        System.out.println(1/10);
        System.out.println(6/10);

    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);


        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if(l1.val < l2.val){
                cur = l1;
                l1 = l1.next;
            }else {
                cur = l2;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }

        /* // 类似归并排序中的合并过程
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        // 任一为空，直接连接另一条链表
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }*/
        return dummyHead.next;
    }
}


