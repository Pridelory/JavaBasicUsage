package com.wangmeng.hashtable;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqChar {
    public int firstUniqChar(String s) {
        Map<Character,Integer> hashMap =new HashMap<>();
        boolean flag = false;
        Integer result = 0;
        int len = s.length();
        for (int i = 0; i<len; i++) {
            if (hashMap.containsKey(s.charAt(i))) {
                hashMap.put(s.charAt(i),hashMap.get(s.charAt(i))+1);
            } else {
                hashMap.put(s.charAt(i),1);
            }

        }
        for (int i =0; i<len; i++) {
            if (hashMap.get(s.charAt(i))==1) {
                flag = true;
                result = i;
                break;
            }
        }
        if (flag ==false) {
            result = -1;
        }
        return result;
    }
    public static void main(String[] args) {
        FirstUniqChar firstUniqChar = new FirstUniqChar();
        firstUniqChar.firstUniqChar("leetcode");
    }
}
