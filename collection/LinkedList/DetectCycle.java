package com.wangmeng.collection.LinkedList;

public class DetectCycle {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            if (head == null)
                return null;
            ListNode walker = head;
            ListNode runner = head;
            while (runner.next != null && runner.next.next != null) {
                walker = walker.next;
                runner = runner.next.next;
                if (walker == runner) {
                    ListNode restart = head;
                    while (restart != runner) {
                        restart = restart.next;
                        runner = runner.next;
                    }
                    return restart;
                }
            }
            return null;
        }
    }
}
