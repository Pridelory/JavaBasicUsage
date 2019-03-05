package com.wangmeng.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 有两种方法，第一种是hashMap(正常解法)
 * 第二种是利用模拟指针
 */
public class Intersect {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> hashMap = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            if (hashMap.containsKey(nums1[i])) {
                hashMap.put(nums1[i],hashMap.get(nums1[i])+1);
            } else {
                hashMap.put(nums1[i],1);
            }
        }

        for (int i =0; i < nums2.length; i++) {
            if (hashMap.containsKey(nums2[i])) {
                list.add(nums2[i]);
                hashMap.put(nums2[i],hashMap.get(nums2[i])-1);
                if (hashMap.get(nums2[i])==0) {
                    hashMap.remove(nums2[i]);
                }
            }
        }
        int[] array = new int[list.size()];
        for (int i=0; i<list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
    public static void main(String[] args) {
        Intersect intersect = new Intersect();
        intersect.intersect(new int[]{4,9,5},new int[]{9,4,9,8,4});
    }
}
