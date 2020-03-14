package com.javaSE.comparator;



import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 测试Comparable接口
 */
public class TestComparable {

    @Test
    public void test() {
        Person p1 = new Person("cca", 21);
        Person p2 = new Person("bad", 25);
        Person p3 = new Person("bada", 18);
        Person p4 = new Person("oaz", 34);
        Person p5 = new Person("ssd", 80);

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        //排序前打印
        for (Person p : list) {
            System.out.println(p);
        }

        System.out.println("---------------------------");
        //排序
        Collections.sort(list);

        //排序后打印
        for (Person p : list) {
            System.out.println(p);
        }
    }
}








