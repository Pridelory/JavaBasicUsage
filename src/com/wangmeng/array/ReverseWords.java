package com.wangmeng.array;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

/**
 * Reverse words in a String
 */
public class ReverseWords {

    /**
     * 1、transfer the s to the LinkedList
     * 2、reverse the LinkedList
     * 3、transfer the LinkedList to String type and return
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        //Returns a string whose value is this string, with any leading and trailing whitespace removed.
        String str = s.trim();
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        LinkedList<String> list = new LinkedList<>();
        //tranverse the chars
        for (int i = 0; i < chars.length; i++) {
            if (!Character.isWhitespace(chars[i])) {
                sb.append(chars[i]);
            } else if (sb.length() != 0){
                //space
                list.addLast(sb.toString());
                sb.delete(0,sb.length());
            }
        }
        list.add(sb.toString());

        LinkedList<String> newList = new LinkedList<>();
        int len = list.size();
        for (int i = 0; i < len; i++) {
            newList.addFirst(list.getFirst());
            list.pop();
        }
        StringBuilder newSb = new StringBuilder();
        while (newList.size() != 0 ) {
            newSb.append(newList.getFirst());
            newSb.append(" ");
            newList.pop();
        }
        return newSb.toString().trim();
    }

    /**
     * Input: "Let's take LeetCode contest"
     * Output: "s'teL ekat edoCteeL tsetnoc"
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        String[] array = s.split(" ");
        StringBuffer sb=new StringBuffer();
        for (String str : array) {
            sb.append(reverse(str));
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * 反转String
     */
    private String reverse(String str) {
        char[] chars = str.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
        return new String(chars);
    }


    @Test
    public void test1() {
        String string = "  i   love u";
        String s = reverseWords(string);
        System.out.println(s);
    }

    @Test
    public void test2() {
        String s = "Let's take LeetCode contest";
        String s1 = reverseWords2(s);
        System.out.println(s1);
    }
}
