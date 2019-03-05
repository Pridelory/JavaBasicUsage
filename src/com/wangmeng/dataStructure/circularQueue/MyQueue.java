package com.wangmeng.dataStructure.circularQueue;

import java.util.*;

class MyQueue {
    Stack<Integer> mainStack = null;
    Stack<Integer> auxiliaryStack = null;
    /** Initialize your data structure here. */
    public MyQueue() {
        mainStack = new Stack<>();
        auxiliaryStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if (mainStack.size() != 0) {
            while (mainStack.size() != 0) {
                auxiliaryStack.push(mainStack.pop());
            }
        }
        mainStack.push(x);
        while (auxiliaryStack.size() != 0) {
            mainStack.push(auxiliaryStack.pop());
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return mainStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        return mainStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return mainStack.size() == 0;
    }
}
