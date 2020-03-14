package com.javaSE.HashCodeAndEquals;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;

public class TestUsage {

    @Test
    public void test1() {
        Person p1 = new Person("王萌", 19);
        Person p2 = new Person("王蒙", 19);
        //明显可见比较的是p1和p2的地址
        System.out.println(p1 == p2);
        //true
        System.out.println(p1 == p1);
        //如果Person没有覆写equals()，则调用Object里的equals(),实际上还是用"=="比较的
        //当Person覆写equals()时，则根据比较规则来判定了
        System.out.println(p1.equals(p2));
        System.out.println(p1.getAge() == p2.getAge());
        System.out.println("----------------------");
        //Integer比较测试
        Integer i1 = 13;
        Integer i2 = 12;
        System.out.println(i1 == i2);
    }

    @Test
    public void test2() {
        HashSet<Person> hashSet = new HashSet<>();
        hashSet.add(new Person("小明", 18));
        hashSet.add(new Person("小明", 18));
        Iterator<Person> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test3() {
        // 字符串
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1 == s2);
    }

    @Test
    public void test4() {
        // 字符串
        Person person = new Person("小名", 30);
        Person person1 = new Person("小名", 30);
        System.out.println(person.hashCode());
        System.out.println(person1.hashCode());
    }
}
