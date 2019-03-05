package com.wangmeng.dataStructure.stack;

import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        char[] array = s.toCharArray();
        if (array.length % 2 != 0) {
            return false;
        }
        //initialize the Stack
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < array.length ; i++) {
            if (stack.size() == 0) {
                stack.push(array[i]);
                continue;
            }
            if (stack.peek() == '(' && array[i] == ')') {
                stack.pop();
            } else if (stack.peek() == '{' && array[i] == '}') {
                stack.pop();
            } else if (stack.peek() == '[' && array[i] == ']') {
                stack.pop();
            } else {
                stack.push(array[i]);
            }
        }
        if (stack.size() != 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        boolean valid = new ValidParentheses().isValid("{[]}");
        System.out.println(valid);
    }
}
