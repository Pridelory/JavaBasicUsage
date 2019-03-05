package com.wangmeng.collection.LinkedList;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

/**
 * java内置LinkedList底层
 */
public class BasicUsage {

    @Test
    public void test() {
        LinkedList<Integer> list = new LinkedList<>();
//        Integer first = list.getFirst();
        list.addFirst(1);
        int i = list.indexOf(null);
        System.out.println(i);
    }
}
