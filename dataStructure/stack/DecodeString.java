package com.wangmeng.dataStructure.stack;

import java.util.ArrayDeque;
import java.util.Queue;

public class DecodeString {

    public String decodeString(String s) {
        Queue<Character> q = new ArrayDeque<>();
        for (char c : s.toCharArray()) q.offer(c);
        q.offer(']');
        return decode(q);
    }

    private String decode(Queue<Character> q) {

        int num = 0;
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            char c = q.poll();
            if (Character.isLetter(c)) sb.append(c);
            else if (Character.isDigit(c)) num = num*10 + c -'0';
            else if (c == ']') break;
            else {
                String nested = decode(q);
                for (int i=0; i<num; i++) sb.append(nested);
                num = 0;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "3[a2[c]]";
        String string = new DecodeString().decodeString(s);
        System.out.println(string);
    }
}
