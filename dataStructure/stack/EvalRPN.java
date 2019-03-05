package com.wangmeng.dataStructure.stack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class EvalRPN {
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        Set<String> ops = new HashSet<>();
        ops.addAll(Arrays.asList("+", "-", "/", "*"));
        for (int i = 0; i < tokens.length; i++) {
            if (ops.contains(tokens[i])) {
                String res;
                String temp1 = stack.pop();
                String temp2 = stack.pop();
                if (tokens[i].equals("+")) {
                    res = Integer.toString(Integer.parseInt(temp2) + Integer.parseInt(temp1));
                } else if (tokens[i].equals("-")) {
                    res = Integer.toString(Integer.parseInt(temp2) - Integer.parseInt(temp1));
                } else if (tokens[i].equals("*")) {
                    res = Integer.toString(Integer.parseInt(temp2) * Integer.parseInt(temp1));
                } else {
                    res = Integer.toString(Integer.parseInt(temp2) / Integer.parseInt(temp1));
                }
                stack.push(res);
            } else {
                stack.push(tokens[i]);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int i = new EvalRPN().evalRPN(strs);
        System.out.println(i);
    }
}
