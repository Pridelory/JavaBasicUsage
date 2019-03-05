package com.wangmeng.array;

import org.junit.jupiter.api.Test;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] == val) {
                for (int j = i; j < nums.length - 1; j++) {
                    if (j != length - 1) {
                        nums[j] = nums[j + 1];
                    }
                }
                i--;
                length--;
            }
        }
        return length;
    }

    /**
     * Max Consecutive Ones
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                temp++;
            } else {
                //清算
                temp = 0;
            }
            count = Math.max(count,temp);
        }
        return count;
    }

    @Test
    public void test1() {
        int[] array = new int[]{0,1,2,2,3,0,4,2};
        int i = removeElement(array, 2);
        for (int j = 0; j < i; j++) {
            System.out.print(array[j]);
        }
    }

    @Test
    public void test2() {
        int[] array = new int[]{1,1};
        int maxConsecutiveOnes = findMaxConsecutiveOnes(array);
        System.out.println(maxConsecutiveOnes);
    }
}
