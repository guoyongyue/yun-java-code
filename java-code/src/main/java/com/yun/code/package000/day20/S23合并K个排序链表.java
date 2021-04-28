package com.yun.code.package000.day20;

public class S23合并K个排序链表 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;
        int k = lists.length;
        while (k > 1) {
            for (int i = 0; i < k / 2; i++)
                lists[i] = mergeTwoLists(lists[i], lists[i + (k + 1) / 2]);
            k = (k + 1) / 2;
        }
        return lists[0];
    }

    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode root = new ListNode(0);
        ListNode node = root;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                node.next = list1;
                list1 = list1.next;
            } else {
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }
        if (list1 == null)
            node.next = list2;
        if (list2 == null)
            node.next = list1;
        return root.next;
    }


}


/*class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}*/
