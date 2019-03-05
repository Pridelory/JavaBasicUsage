package com.wangmeng.collection.LinkedList;

/**
 * 数据结构之我的链表LinkedList
 */
public class MyLinkedList {

    private Node head;

    private int size;

    private class Node {
        public int val;
        public Node next;

        public  Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
        public Node(int val) {
            this.val = val;
        }
    }

    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = new Node(-1,null);   //设有头结点
        size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        return prev.next.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node first = new Node(val,null);
        if (head.next == null) {
            head.next = first;
        } else {
            first.next = head.next;
            head.next = first;
        }
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node prev = head;
        Node last = new Node(val,null);
        for (int i = 0; i < size; i++) {
            prev = prev.next;
        }
        prev.next = last;
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index >= 0 && index <= size) {
            Node prev = head;
            Node inseredNode = new Node(val,null);
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            inseredNode.next = prev.next;
            prev.next = inseredNode;
            size++;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index >= 0 && index <size) {
            Node prev = head;
            for (int i = 0; i < index; i++){
                prev = prev.next;
            }
            prev.next = prev.next.next;
            size--;
        }
    }

    /**
     * print the val in each node in order
     */
    public void printLinkedList() {
        Node node = head.next;
        for (int i = 0; i < size; i++) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        //构造LinkedList
        MyLinkedList myLinkedList = new MyLinkedList();
        for (int i = 0; i< 10; i++) {
            myLinkedList.addAtTail(i);
        }
        //测试get()
//        int i = myLinkedList.get(1);
//        System.out.println(i);

//        myLinkedList.addAtHead(100);
        myLinkedList.printLinkedList();
    }
}
