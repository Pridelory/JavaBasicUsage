package com.wangmeng.dataStructure.stack;

import java.util.Stack;

/**
 * use implicit as well as explicit stack
 * DFS method
 */
public class TargetSum {
    int res = 0;
    public int findTargetSumWays1(int[] nums, int S) {
        DFS1(nums, S, 0, 0);
        return res;
    }

    //implicit stack
    public void DFS1(int[] nums, int S, int position, int currentSum) {
        if (position == nums.length) {
            if (currentSum == S) {
                res++;
            }
            return;
        }
        DFS1(nums, S, position + 1, currentSum + nums[position]);
        DFS1(nums, S, position + 1, currentSum - nums[position]);
    }

    //explicit stack
    public void DFS2() {

    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 1, 1};
        int res = new TargetSum().findTargetSumWays1(nums, 3);
        System.out.println(res);
    }
}
