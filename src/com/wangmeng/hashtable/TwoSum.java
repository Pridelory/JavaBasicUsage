package com.wangmeng.hashtable;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashmap = new HashMap<>();
        int result[] =new int[2];
        for (int j=0; j<nums.length; j++) {
            if (hashmap.containsKey(target - nums[j])) {
                result[0] = hashmap.get(target - nums[j]);
                result[1] = j;
                return result;
            }else {
                hashmap.put(nums[j],j);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        twoSum.twoSum(new int[] {3,2,4},6);
    }
}
