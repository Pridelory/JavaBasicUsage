package com.wangmeng.hashtable;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MinimumIndexSumofTwoLists {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String,Integer> hashMap = new HashMap<>();
        //用于存目标字符串
        LinkedList<String> list = new LinkedList<>();
        int minValue = Integer.MAX_VALUE;
        int list1Len = list1.length;
        int list2Len = list2.length;
        //把数组list1加入hashMap
        for (int i =0;i<list1Len; i++) {
            hashMap.put(list1[i] ,i);
        }
        for (int i =0; i<list2Len; i++) {
            if (hashMap.containsKey(list2[i])) {
                if ((i + hashMap.get(list2[i])<minValue)) {
                    list.clear();
                    list.add(list2[i]);
                    minValue = i + hashMap.get(list2[i]);
                } else if ((i + hashMap.get(list2[i]))== minValue){
                    list.add(list2[i]);
                }
            }
        }
        String[] strings = new String[list.size()];
        String[] array = list.toArray(strings);
        return array;
    }
    public static void main(String[] args) {
        MinimumIndexSumofTwoLists minimumIndexSumofTwoLists = new MinimumIndexSumofTwoLists();
        minimumIndexSumofTwoLists.findRestaurant(new String[] {"Shogun", "Tapioca Express", "Burger King", "KFC"} ,new String[] {"KFC", "Shogun", "Burger King"});
    }
}
