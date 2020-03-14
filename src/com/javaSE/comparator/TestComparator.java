package com.javaSE.comparator;



import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 测试外部比较器Comparator
 */
public class TestComparator implements Comparator<Person>{

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getAge() - p2.getAge();
    }

    @Test
    public void test() {
        Person p1 = new Person("cca", 21);
        Person p2 = new Person("bad", 25);
        Person p3 = new Person("zzz", 18);
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
        Collections.sort(list, new TestComparator());

        //排序后打印
        for (Person p : list) {
            System.out.println(p);
        }
    }
}
