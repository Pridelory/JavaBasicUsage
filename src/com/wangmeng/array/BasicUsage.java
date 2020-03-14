package com.wangmeng.array;


import org.junit.Test;

/**
 * Array基本用法
 */
public class BasicUsage {

    /**
     * System.arraycopy()用法
     * 特性：方便
     */
    @Test
    public void test1() {
        int[] a1 = new int[]{1,2,3,4,5,6,7};
        int[] a2 = new int[7];
        System.arraycopy(a1,4,a2,0,3);
        System.arraycopy(a1,0,a2,3,4);
        System.out.println();
    }

    @Test
    public void test2() {
        String string = "12345";
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int j = 1;
            chars[i] = (char) ((chars[i] - '0' + j + 10) % 10 + '0');
        }
        System.out.println(new String(chars));
    }
}
