package com.javaSE.collection;

/**
 * 测试左移右移
 */
public class TestMoveBit {
    public static void main(String[] args) {
        int i = 12;
        printBinaryInfo(i);
        System.out.println("-----------------------------------");
        int result = i << 1;
        printBinaryInfo(result);
        System.out.println(result);
    }

    public static void printBinaryInfo(int num) {
        System.out.println("十进制为" + num);
        System.out.println("二进制为" + Integer.toBinaryString(num));
    }
}
