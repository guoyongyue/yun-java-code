package com.yun.code.package0300.package80;

import com.yun.code.ListNode;

import java.util.Random;

/**
 * 给定一个单链表，随机选择链表的一个节点，并返回相应的节点值。保证每个节点被选的概率一样。
 *
 * 进阶:
 * 如果链表十分大且长度未知，如何解决这个问题？你能否使用常数级空间复杂度实现？
 *
 */
public class S382链表随机节点 {
    private ListNode head;
    public S382链表随机节点(ListNode head) {
        this.head=head;
    }

    public static void main(String[] args) {

    }
    /** Returns a random node's value. */
    public int getRandom() {
        Random random = new Random();
        ListNode node = head.next;
        int retVal=head.val;
        int i=2;
        while(node!=null){
            if(random.nextInt(i)==i){
                retVal = node.val;
            }
            node=node.next;
            i++;
        }
        return retVal;
    }
}
