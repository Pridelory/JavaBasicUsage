package com.wangmeng.hashtable;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    public boolean isHappy(int n) {
        int sum =0;
        int k =0;
        Set<Integer> hashSet = new HashSet<>();
        while (k !=1) {
            int m =0;
            sum =0;
            while(n!=0) {
                m =n % 10;
                sum = sum + m * m;
                n = n / 10;
            }
            if (hashSet.contains(sum)) {
                return false;
            }else {
                hashSet.add(sum);
            }
            n = sum;
            k = sum;
        }
        return true;
    }

    public static void main(String[] args) {
        HappyNumber happyNumber = new HappyNumber();
        boolean flag = happyNumber.isHappy(19);
        System.out.println(flag);
    }
}

