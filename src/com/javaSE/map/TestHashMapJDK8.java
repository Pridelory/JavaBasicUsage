package com.javaSE.map;

import java.util.HashMap;

/**
 * JDK8下的hashMap
 */
public class TestHashMapJDK8 {
    public static void main(String[] args) {
        new HashMap<String, String>();
    }
}

/*
JDK8中HashMap是数组+链表+红黑树实现的

一、字段属性
默认 HashMap 集合初始容量为16（必须是 2 的倍数）
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

集合的最大容量，如果通过带参构造指定的最大容量超过此数，默认还是使用此数
static final int MAXIMUM_CAPACITY = 1 << 30;

默认的填充因子
static final float DEFAULT_LOAD_FACTOR = 0.75f;

当桶(bucket)上的结点数大于这个值时会转成红黑树(JDK1.8新增)
static final int TREEIFY_THRESHOLD = 8;

当桶(bucket)上的节点数小于这个值时会转成链表(JDK1.8新增)
static final int UNTREEIFY_THRESHOLD = 6;

(JDK1.8新增)
当集合中的容量大于这个值时，表中的桶才能进行树形化 ，否则桶内元素太多时会扩容，
而不是树形化 为了避免进行扩容、树形化选择的冲突，这个值不能小于 4 * TREEIFY_THRESHOLD
static final int MIN_TREEIFY_CAPACITY = 64;

初始化使用，长度总是 2的幂
transient Node<K,V>[] table;

保存缓存的entrySet（）
transient Set<Map.Entry<K,V>> entrySet;

此映射中包含的键值映射的数量。（集合存储键值对的数量）
transient int size;

跟前面ArrayList和LinkedList集合中的字段modCount一样，记录集合被修改的次数
主要用于迭代器中的快速失败（fail-fast）
transient int modCount;

调整大小的下一个大小值（容量*加载因子）。capacity * load factor
int threshold;

散列表的加载因子。
final float loadFactor;

二、初始化过程
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
        // tableSizeFor()的主要功能是返回一个比给定整数大且最接近的2的幂次方整数，如给定10，返回2的4次方16.
        this.threshold = tableSizeFor(initialCapacity);
    }

注：JDK8把确保初始容量的规则放到了构造方法中（JDK7是roundUpToPowerOf2）

三、put过程

final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        // 如果table为null或者长度为0，则进行初始化
        if ((tab = table) == null || (n = tab.length) == 0)
            // resize()方法本来是用于扩容，由于初始化没有实际分配空间，这里用该方法进行空间分配，后面会详细讲解该方法
            n = (tab = resize()).length;

        //(n - 1) & hash：确保索引在数组范围内,相当于hash % n 的值
        //通过 key 的 hash code 计算其在数组中的索引：为什么不直接用 hash 对 数组长度取模？因为除法运算效率低
        if ((p = tab[i = (n - 1) & hash]) == null)
            // tab[i] 为null，直接将新的key-value插入到计算的索引i位置
            tab[i] = newNode(hash, key, value, null);
        else {
            // tab[i] 不为null，表示该位置已经有值了
            Node<K,V> e; K k;
            // e节点表示已经存在Key的节点，需要覆盖value的节点
            // table[i]的首个元素是否和key一样,如果相同直接覆盖value
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                // 节点key已经有值了，将第一个节点赋值给e
                e = p;
            else if (p instanceof TreeNode)
                //该链是红黑树
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                // 该链是链表
                // 遍历链表
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        // 链表长度大于8，转换成红黑树
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    // //key已经存在直接终止，此时e的值已经为 p.next
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    // 修改已经存在Key的节点的value
                    e.value = value;
                afterNodeAccess(e);
                // 返回key的原始值
                return oldValue;
            }
        }
        // 用作修改和新增快速失败
        ++modCount;
        // 超过最大容量，进行扩容
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }

四、扩容过程

final Node<K,V>[] resize() {
        // 将原始数组数据缓存起来
        Node<K,V>[] oldTab = table;
        // //原数组如果为null，则长度赋值0
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        // 如果原数组长度大于0
        if (oldCap > 0) {
            // 数组大小如果已经大于等于最大值(2^30)
            if (oldCap >= MAXIMUM_CAPACITY) {
                // 修改阈值为int的最大值(2^31-1)，这样以后就不会扩容了
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            // 原数组长度扩大1倍(此时将原数组扩大一倍后的值赋给newCap)也小于2^30次方，
            // 并且原数组长度大于等于初始化长度16
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        else if (oldThr > 0) // initial capacity was placed in threshold
            // 旧容量为0，旧阀值大于0，则将新容量直接等于就阀值
            newCap = oldThr;
        else {               // zero initial threshold signifies using defaults
            // 阀值等于0，oldCap也等于0（集合未进行初始化）
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        // 此时就是上面原数组长度扩大一倍后大于MAXIMUM_CAPACITY和旧容量为0、旧阀值大于0的情况
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                      (int)ft : Integer.MAX_VALUE);
        }

        // 将阀值上限设置为新阀值上限
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})

        // 创建容器大小为newCap的新数组
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        // 将新数组赋给table
        table = newTab;

        // 如果是第一次,扩容的时候,也就是原来没有元素,下面的代码不会运行,
        // 如果原来有元素,则要将原来的元素,进行放到新扩容的里面
        // 以下为transfer的代码
        if (oldTab != null) {
            // 把每个bucket都移动到新的buckets中
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                if ((e = oldTab[j]) != null) {
                    // 元数据j位置置为null，便于垃圾回收!!!
                    oldTab[j] = null;
                    // 数组没有下一个引用（不是链表）
                    if (e.next == null)
                        //直接将e的key的hash与新容量重新计算下标,新下标的元素为e
                        newTab[e.hash & (newCap - 1)] = e;
                    else if (e instanceof TreeNode)
                        //红黑树
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else {
                        // preserve order
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }


if ((e.hash & oldCap) == 0)如果判断成立，那么该元素的地址在新的数组中就不会改变。因为oldCap的最高位的1，
在e.hash对应的位上为0，所以扩容后得到的地址是一样的，位置不会改变 ，在后面的代码的执行中会放到loHead中去，
最后赋值给newTab[j]；如果判断不成立，那么该元素的地址变为 原下标位置+oldCap，也就是lodCap最高位的1，
在e.hash对应的位置上也为1，所以扩容后的地址改变了，在后面的代码中会放到hiHead中，最后赋值给newTab[j + oldCap] 举个栗子来说一下上面的两种情况：
设：oldCap=16 二进制为：0001 0000
oldCap-1=15 二进制为：0000 1111
e1.hash=10 二进制为：0000 1010
e2.hash=26 二进制为：0101 1010
e1在扩容前的位置为：e1.hash & oldCap-1 结果为：0000 1010
e2在扩容前的位置为：e2.hash & oldCap-1 结果为：0000 1010
结果相同，所以e1和e2在扩容前在同一个链表上，这是扩容之前的状态。 现在扩容后，需要重新计算元素的位置，
在扩容前的链表中计算地址的方式为e.hash & oldCap-1 那么在扩容后应该也这么计算呀，
扩容后的容量为oldCap*2=32 0010 0000 newCap=32，新的计算 方式应该为
e1.hash & newCap-1
即：0000 1010 & 0001 1111
结果为0000 1010与扩容前的位置完全一样。
e2.hash & newCap-1
即：0101 1010 & 0001 1111
结果为0001 1010,为扩容前位置+oldCap。
而这里却没有e.hash & newCap-1
而是 e.hash & oldCap，其实这两个是等效的，都是判断倒数第五位是0，还是1。如果是0，则位置不变，是1则位置改变为扩容前位置+oldCap。


五、查找元素
public V get(Object key) {
    Node<K,V> e;
    return (e = getNode(hash(key), key)) == null ? null : e.value;
}

final Node<K,V> getNode(int hash, Object key) {
    Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & hash]) != null) {
        //根据key计算的索引检查第一个索引
        if (first.hash == hash && // always check first node
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        //不是第一个节点
        if ((e = first.next) != null) {
            if (first instanceof TreeNode)//遍历树查找元素
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            do {
                //遍历链表查找元素
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}



*/