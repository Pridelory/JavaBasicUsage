package com.wangmeng.recursion;

import com.wangmeng.dataStructure.ListNode;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 链表递归操作
 */
public class LinkedListRecursion {

    /**
     * 递归倒序打印单链表的值
     *
     * @return
     */
    public void printLinkedListConversely(ListNode head) {
        ListNode temp = head;
        if (temp != null) {
            printLinkedListConversely(temp.next);
            System.out.println(temp.val);
        }
    }

    /**
     * 递归把LinkedList反转
     *
     * @return
     */

    ListNode dummy = new ListNode(-1);

    //    ListNode p = dummy;
    public void reverseLinkedList(ListNode head) {
        ListNode first = head;
        ListNode rest = first.next;
        //终止条件
        if (rest == null) {
            dummy.next = first;
//            p = first;
            return;
        }
        //递归操作
        reverseLinkedList(rest);
        //拼接操作
        first.next.next = first;
        first.next = null;
//        p.next = first;
//        first.next = null;
//        p = p.next;
    }

    /**
     * remove LinkedList elements
     * recursion method
     */
    public ListNode removeElements(ListNode head, int val) {
        //终止条件
        if (head == null) {
            return null;
        }
        ListNode listNode = removeElements(head.next, val);
        if (head.val == val) {
            return listNode;
        } else {
            head.next = listNode;
            return head;
        }
    }

    /**
     * isPalindrome
     */
    ListNode temp = null;

    public boolean isPalindrome(ListNode head) {
        temp = head;
        return check(head);
    }

    public boolean check(ListNode head) {
        if (head == null) {
            return true;
        }
        boolean check = check(head.next);
        boolean compare = temp.val == head.val ? true : false;
        temp = temp.next;
        return check && compare;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode tail = head;
        int length = 1;
        //tranverse and count the length of the LinkedList
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        //find the start after rotating
        int start = k % length;
        if (start == 0) return head;

        ListNode temp = head;
        for (int i = 0; i < length - start - 1; i++) {
            temp = temp.next;
        }
        //new start node
        ListNode newStart = temp.next;
        //cut
        temp.next = null;
        tail.next = head;
        return newStart;
    }

    @Test
    public void test() {
        ListNode head = new ListNode(0);
        ListNode p = head;
        p.next = new ListNode(1);
        p = p.next;
//        p.next = new ListNode(6);
//        p = p.next;
        p.next = new ListNode(2);
//        p = p.next;
//        p.next = new ListNode(4);
//        p = p.next;
//        p.next = new ListNode(3);
//        p = p.next;
//        p.next = new ListNode(2);
//        p = p.next;
//        p.next = new ListNode(1);
//        printLinkedListConversely(head);
//        reverseLinkedList(head);
//        ListNode q = removeElements(head, 6);
//        ListNode q = dummy.next;
//        boolean palindrome = isPalindrome(head);
//        System.out.println(palindrome);
//

        ListNode listNode = rotateRight(head, 4);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
            System.out.println(1);
//        }
        }
    }
}
