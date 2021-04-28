package com.yun.code.package000.day20;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String valueArray = sc.nextLine();
        String valueNum = sc.nextLine();

        ListNode listNode = new ListNode(1);
        String[] split = valueArray.substring(1, valueArray.length()).split(",");

        for (int i = 1; i < split.length; i++) {
            listNode.next=new ListNode(Integer.valueOf(split[i]));
        }

        ListNode listNode1 = reverseKGroup(listNode, Integer.valueOf(valueNum));
        System.out.print("[");
        while(listNode1 !=null){
            System.out.print(listNode1.val+",");
            listNode1=listNode1.next;
        }
        System.out.print("]");
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;
        ListNode check = head;
        int canProceed = 0;
        int count = 0;
        while (canProceed < k && check != null) {
            check = check.next;
            canProceed++;
        }
        if (canProceed == k) {
            while (count < k && cur != null) {
                next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
                count++;
            }
            if (next != null) {
                head.next = reverseKGroup(next, k);
            }
            return prev;
        } else {
            return head;
        }
    }
}

class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int val){
        this.val=val;
    }
}