package com.wangmeng.hashtable;

import java.lang.reflect.Array;
import java.util.*;

public class GroupAnagrams {
    public static String sortString(String s) {
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        return String.valueOf(charArray);
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> hashMap = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String s = sortString(strs[i]);
            if (!hashMap.containsKey(s)) {
                hashMap.put(s, new ArrayList<String>());
            }
                hashMap.get(s).add(strs[i]);
        }
//        ArrayList<List<String>> list = new ArrayList<>();
//        for (String key : hashMap.keySet()) {
//            list.add(hashMap.get(key));
//        }
        return new ArrayList<List<String>>(hashMap.values());
    }
    public static void main(String[] args) {
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        groupAnagrams.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
    }
}
