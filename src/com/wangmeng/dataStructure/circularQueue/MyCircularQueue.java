package com.wangmeng.dataStructure.circularQueue;


import java.util.LinkedList;
import java.util.Stack;

/**
 * MyCircularQueue
 */
public class MyCircularQueue {

    final int[] array;    //定义数组
    int len = 0;
    int backPoint = -1;  //尾指针
    int frontPoint = -1; //头指针
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        array = new int[k];
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (!isFull()) {
            backPoint = (backPoint + 1) % array.length;
            array[backPoint] = value;
            len++;
            return true;
        } else {
            return false;
        }
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (!isEmpty()) {
            frontPoint = (frontPoint + 1) % array.length;
            len--;
            return true;
        } else {
            return false;
        }
    }
    /** Get the front item from the queue. */
    public int Front() {
        return isEmpty() ? -1 : array[frontPoint];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        return isEmpty() ? -1 : array[backPoint];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return len == 0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return len == array.length;
    }

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // set the size to be 3
        circularQueue.enQueue(1);  // return true
        circularQueue.enQueue(2);  // return true
        circularQueue.enQueue(3);  // return true
        circularQueue.enQueue(4);  // return false, the queue is full
        circularQueue.Rear();  // return 3
        circularQueue.isFull();  // return true
        circularQueue.deQueue();  // return true
        circularQueue.enQueue(4);  // return true
        circularQueue.Rear();  // return 4
    }
}
