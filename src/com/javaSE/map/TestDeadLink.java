package com.javaSE.map;

/**
 * 死链测试
 */
public class TestDeadLink {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;

        Node p = node1;
        do {
            System.out.println(p.value);
            p = p.next;
        } while (null != p);
    }


    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }
}
