package com.wangmeng.collection.LinkedList;

import com.wangmeng.dataStructure.*;
import org.junit.jupiter.api.Test;

/**
 * 合并两个LinkedList(值从小到大)
 * 用两种方法
 * 正常方法和递归法
 */
public class MergeTwoSortedLists {

    /**
     * 正常方法
     */
    public ListNode normalMerge(ListNode l1, ListNode l2) {
        //存放结果值
        ListNode result = new ListNode(-1);
        //遍历指针
        ListNode temp = result;
        //循环遍历
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        if (l1 == null) {
            //把l2剩余结点拼接到result
            temp.next = l2;
        } else if (l2 == null) {
            temp.next = l1;
        }
        return result.next;
    }

    /**
     * 递归方法
     */
    public ListNode recursionMerge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val <= l2.val) {
            l1.next = recursionMerge(l1.next, l2);
            return l1;
        } else {
            l2.next = recursionMerge(l1,l2.next);
            return l2;
        }
    }

    @Test
    public void test1 () {
        ListNode head1 = new ListNode(1);
        ListNode p = head1;
        p.next = new ListNode(2);
        p = p.next;
        p.next = new ListNode(4);

        ListNode head2 = new ListNode(1);
        ListNode q = head2;
        q.next = new ListNode(3);
        q = q.next;
        q.next = new ListNode(4);

//        ListNode listNode = normalMerge(head1, head2);
        ListNode listNode = recursionMerge(head1, head2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
