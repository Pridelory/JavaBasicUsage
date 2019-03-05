package com.wangmeng.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequent {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> hashMap = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        List<Integer>[] bucket = new List[nums.length + 1];
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            hashMap.put(nums[i],hashMap.getOrDefault(nums[i],0)+1);
        }
        for (int key : hashMap.keySet()) {
            int frequency = hashMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }
        List<Integer> res = new ArrayList<>();
        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        TopKFrequent topKFrequent = new TopKFrequent();
        topKFrequent.topKFrequent(new int[]{1,1,1,2,2,3},2);
    }
}
