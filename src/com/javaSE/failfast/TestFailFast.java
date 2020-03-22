package com.javaSE.failfast;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 测试fail-fast机制
 */
public class TestFailFast {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next.intValue() == 2) {
                // 以下语句会导致抛出ConcurrentModificationException异常
//                list.remove(next.intValue());
                // 使用iterator的remove则不会导致抛出
                iterator.remove();
                continue;
            }
            System.out.println(next);
        }

        // 多线程下
//        Iterator<Integer> iterator2= list.iterator();
//        Object lock = new Object();
//        while (iterator.hasNext()) {
//            Integer next = iterator.next();
//            // 加锁保证安全性
//            synchronized (lock) {
//                if (next.intValue() == 2) {
//                    iterator.remove();
//                    continue;
//                }
//                System.out.println(next);
//            }
//        }
    }
}

/*
当遍历ArrayList集合时，如果用ArrayList下的方法进行add remove等操作会导致抛出ConcurrentModificationException异常，
这就是fail-fast机制，与其让有漏洞的程序继续执行，还不如发现程序不安全时，立马抛出异常，所以说这也是一种程序bug检测机制。
原理如下：
在获取iterator对象时，会执行expectedModCount = modCount; 在这个iterator生命周期内expectedModCount就固定不变了（除非调用iterator的
remove方法），如果此时调用ArrayList下的方法进行add remove等操作会导致modCount的变化。等到下次iterator.next()执行时会首先去检查
expectedModCount 是否等于 modCount，如果不等，就抛出异常。

怎么解决这种单线程下不安全问题呢？
第一种办法：
用iterator内的remove方法，因为此方法会同时修改modCount和expectedModCount，并令其相等。所以就不会抛出异常了，换句话说此种方法保证了
程序的安全性。

在多线程环境下，则要给临界区代码段加锁，代码如下：
    Iterator<Integer> iterator2= list.iterator();
    while (iterator.hasNext()) {
        Integer next = iterator.next();
        synchronized (this) {
            if (next.intValue() == 2) {
                iterator.remove();
                continue;
            }
            System.out.println(next);
        }
    }


第二种办法：
使用CopyOnWriteArrayList代替ArrayList
这是实现并发的一种新思路，实行读写分离，如果是写操作，则复制一个新集合，在新集合内添加或者删除元素。
待这一切修改完成后，再将原集合的引用指向新的集合。这样的好处是可以高并发地对COW进行读和遍历操作，而不需要加锁。
 */
