//package com.javaSE.comparator;
//
//
//
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
///**
// * 测试Comparable接口
// */
//public class TestComparable {
//
//    @Test
//    public void test() {
//        Person p1 = new Person("cca", 21, new Integer(23));
//        Person p2 = new Person("bad", 25, new Integer(12));
//        Person p3 = new Person("bada", 25, new Integer(222));
//        Person p4 = new Person("oaz", 80, new Integer(199));
//        Person p5 = new Person("ssd", 34, new Integer(84));
//
//        List<Person> list = new ArrayList<>();
//        list.add(p1);
//        list.add(p2);
//        list.add(p3);
//        list.add(p4);
//        list.add(p5);
//        //排序前打印
//        list.forEach((person -> {
//            System.out.println(person);
//        }));
//
//        System.out.println("----------------------------------");
//        //排序
//        Collections.sort(list);
//
//        //排序后打印
//        list.forEach(person -> {
//            System.out.println(person);
//        });
//    }
//}
//
//
//
//
//
//
//
//
