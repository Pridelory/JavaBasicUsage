package com.wangmeng.hashtable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
//        Map m = new HashMap();
//        for (Integer i=0; i<s.length(); ++i)
//            if (m.put(s.charAt(i), i) != m.put(t.charAt(i)+"", i))
//                return false;
//        return true;
        if (s.length() ==1 || t.length() ==1) {
            return true;
        }
        char ss[] = s.toCharArray();
        char tt[] = t.toCharArray();
        Map<Character, Character> hashmap_s = new HashMap<Character, Character>();
        Map<Character, Character> hashmap_t = new HashMap<Character, Character>();
        for (int i =0; i<ss.length; i++) {
            if (hashmap_s.containsKey(ss[i])) {
                if (!hashmap_s.get(ss[i]).equals(tt[i])) {
                    return false;
                }
            } else {
                hashmap_s.put(ss[i],tt[i]);
            }
        }
        for (int i =0; i<tt.length; i++) {
            if (hashmap_t.containsKey(tt[i])) {
                if (!hashmap_t.get(tt[i]).equals(ss[i])) {
                    return false;
                }
            } else {
                hashmap_t.put(tt[i],ss[i]);
            }
        }
        return true;
    }
    public static void main(String[] args) {
        IsomorphicStrings isomorphicStrings =new IsomorphicStrings();
        boolean flag =isomorphicStrings.isIsomorphic("aa","ab");
        System.out.println(flag);
    }
}
