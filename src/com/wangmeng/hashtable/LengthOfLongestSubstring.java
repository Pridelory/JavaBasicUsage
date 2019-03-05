package com.wangmeng.hashtable;

import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> hashSet = new HashSet<>();
        int len = s.length();
        int maxCount = 0;
        int result = 0;
        if (len == 1) {
            return 1;
        }
        for (int i = 0; i < len; i++) {
            maxCount = 0;
            hashSet.clear();
            for (int j = i; j < len; j++) {
                if (!hashSet.contains(s.charAt(j))) {
                    hashSet.add(s.charAt(j));
                    maxCount++;
                } else {
                    break;
                }
            }
            if (result <= maxCount) {
                result = maxCount;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        lengthOfLongestSubstring.lengthOfLongestSubstring("aab");
    }
}
