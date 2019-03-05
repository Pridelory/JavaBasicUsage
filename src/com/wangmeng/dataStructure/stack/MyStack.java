package com.wangmeng.dataStructure.stack;

import java.util.LinkedList;
import java.util.Queue;

class MyStack {

    /** Initialize your data structure here. */
    Queue<Integer> queue = null;
    Queue<Integer> store = null;
    public MyStack() {
        queue = new LinkedList<>();
        store = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int front = 0;
        while (queue.size() > 1) {
            store.add(queue.poll());
        }
        front = queue.poll();
        queue = store;
        store = new LinkedList<>();
        return front;
    }

    /** Get the top element. */
    public int top() {
        int front = 0;
        while (queue.size() > 0) {
            front = queue.poll();
            store.add(front);
        }
        queue = store;
        store = new LinkedList<>();
        return front;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty() && store.isEmpty();
    }
}

