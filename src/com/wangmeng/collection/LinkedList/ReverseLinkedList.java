package com.wangmeng.collection.LinkedList;


/**
 * reverse the linkedlist using different methods
 */
public class ReverseLinkedList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
        }
    }

    /**
     * 结点依次向前移动
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = head.next;
        ListNode cross = head.next;
        while (cross.next != null) {
            ListNode temp = cross.next;
            cross.next = temp.next;
            temp.next = pre;
            pre = temp;
        }
        return pre;
    }

    /**
     * 倒转指针
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        head = head.next;
        ListNode pre = null;
        ListNode curr = head;
        ListNode next = curr.next;
        while (next != null) {
            curr.next = pre;
            pre = curr;
            curr = next;
            next = curr.next;
        }
        curr.next = pre;
        return curr;
    }

    /**
     * hashmap启发
     * @param head
     * @return
     */
    public ListNode reverseList3(ListNode head) {
        ListNode p = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = p;
            p = head;
            head = next;
        }
        return p;
    }

    public static void main(String[] args) {
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        ListNode head = new ListNode(-1);
        ListNode p = head;
        p.next = new ListNode(1);
        p = p.next;
        p.next = new ListNode(2);
        p = p.next;
        p.next = new ListNode(3);
        p = p.next;
        p.next = new ListNode(4);
        p = p.next;
        p.next = new ListNode(5);
        p = p.next;
        p.next = null;
        ListNode result = reverseLinkedList.reverseList2(head);
        do {
            System.out.println(result.val);
            result = result.next;
        } while (null != result);
    }
}
