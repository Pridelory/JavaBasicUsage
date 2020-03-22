package com.javaSE.map;

import java.util.HashMap;

/**
 * 测试HashMap
 */
public class TestHashMapJDK7 {
    // 此处用conturrentHashMap就不存在并发问题了
    private static HashMap<Person, String> hashmap = new HashMap<>();
    private static HashMap<Long, Person> map = new HashMap<>();
    public static void main(String[] args) {

//        for (int i = 0; i < 13; i++) {
//            hashmap.put(new Person("wangmeng", i + 10), "1");
//            System.out.println(hashmap.keySet());
//        }

        // 丢对象测试
//        for (int i = 0; i < 2000; i++) {
//            new Thread() {
//                @Override
//                public void run() {
//                    hashmap.put(new Person("wangmeng", 10), "1");
////                    map.put(System.nanoTime(), new Person());
//                }
//            }.start();
//        }
//
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(hashmap.size());

        // putForNull测试
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "wangmeng");
        hashMap.put("age", "18");
        hashMap.put(null, "putForNullTest");
        // key为null会覆盖
        hashMap.put(null, "putForNullTestOverride");
        System.out.println(hashMap.get(null));
    }
}

class Person {

    private String name;

    private Integer age;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}

/*


HashMap 原理：
一、JDK7：
基础数据结构定义为：Entry<K,V>[] table = (Entry<K,V>[]) EMPTY_TABLE;

JDK7的HashMap采用邻接表，即每一个table[i]也都能延伸出一个单链表

关键初始化参数：
// 默认的初始化容量 为16
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
// 默认的加载因子 为0.75
static final float DEFAULT_LOAD_FACTOR = 0.75f;
// 默认的空表（提前定义）
static final Entry<?,?>[] EMPTY_TABLE = {};
// 存入的键值对的个数
transient int size;

初始化
public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                                               initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                                               loadFactor);

        this.loadFactor = loadFactor;
        threshold = initialCapacity;
        init();
    }
可见初始化时threshold被设置成初始化容量，即16

put

public V put(K key, V value) {
        // 第一次put 扩容table
        if (table == EMPTY_TABLE) {
            inflateTable(threshold);
        }
        if (key == null)
            // 当key为空时的处理
            return putForNullKey(value);
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        for (Entry<K,V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }

        modCount++;
        addEntry(hash, key, value, i);
        return null;
    }

可见在第一次put时，要执行inflateTable来进行表的扩容。随后，处理当key为空的情况并返回。
然后，通过比对当前key和table[i]这条链，如果遇到key一样的Entry,则直接进行vlue覆盖，返回oldValue。
否则，就执行addEntry来添加一个Entry.以下将步骤分解：

// 只有在第一次put元素或者构建参数为Map时使用
private void inflateTable(int toSize) {
        // Find a power of 2 >= toSize
        // 返回不小于toSize的最小的2的幂数，最大为MAXIMUM_CAPACITY,类比jdk8的实现中的tabSizeFor的作用
        // 这是保证当客户端用自定义的capacity初始化时，使其符合规范（2的次方）
        int capacity = roundUpToPowerOf2(toSize);
        // 扩容阈值为：(容量*加载因子)和(最大容量+1)中较小的一个
        threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);
        // 创建table数组
        table = new Entry[capacity];
        initHashSeedAsNeeded(capacity);
    }

接下来看addEntry
    void addEntry(int hash, K key, V value, int bucketIndex) {
        // 如果当前Entry个数大于等于threshold 且 要增加的Entry所在链不空
        if ((size >= threshold) && (null != table[bucketIndex])) {
            // 进行扩容 newCapaticy为两倍的oldCapacity
            resize(2 * table.length);
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }

        // 直接添加Entry
        createEntry(hash, key, value, bucketIndex);
    }


    void resize(int newCapacity) {
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        // 初始化新表
        Entry[] newTable = new Entry[newCapacity];
        // 旧表数据向新表迁移
        transfer(newTable, initHashSeedAsNeeded(newCapacity));
        // table指向新表
        table = newTable;
        // 更新threshold
        threshold = (int)Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
    }

扩容过程：第一次new HashMap()时，threshold被初始化为了16，table都没创建（为{}）。然后，当第一个元素
put时，就执行inflate方法，算出new Capacity和threshold以及table = new Entry[capacity];
可知，第一次put以后，capacity为16，threshold为12。等到第十二次put时，才会执行resize把容量扩为32以及
threshold扩为24，依次类推...


提升：
在resize方法里，会执行transfer，也就是把数据从旧表迁移到新表，此举很耗费资源，所以尽可能减少resize次数，
写程序时最好初始化个大概的capacity，以此来提高性能。transfer源码如下：

    void transfer(Entry[] newTable, boolean rehash) {
        int newCapacity = newTable.length;
        for (Entry<K,V> e : table) {
            while(null != e) {
                Entry<K,V> next = e.next;
                if (rehash) {
                    e.hash = null == e.key ? 0 : hash(e.key);
                }
                int i = indexFor(e.hash, newCapacity);
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            }
        }
    }
首先上面诠释了一个很好的倒置链表的过程。需牢记

在多线程并发情况下，此举可能会导致丢对象，原因如下：
1、并发时复制被覆盖
2、在已遍历区间新增元素会丢失
3、新表被覆盖

高并发情况下还会导致另一个问题：死链
注意，JDK8的HashMap解决了并发下死链问题，但没有解决并发下丢对象问题，
因为HashMap本来就不支持高并发

 */











