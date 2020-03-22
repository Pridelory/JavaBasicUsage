package com.javaSE.map;

import java.util.TreeMap;

public class TestTreeMap {
    public static void main(String[] args) {
        TreeMap<Demo, String> treeMap = new TreeMap<>();
        treeMap.put(new Demo(), "value one");
        treeMap.put(new Demo(), "value two");
        System.out.println(treeMap.size());
    }

    static class Demo implements Comparable<Demo> {

        @Override
        public int compareTo(Demo o) {
            return -1;
        }
    }
}

/*
TreeMap通过实现Comparable接口或者传入Comparator(key没实现Comparable接口)来实现key去重
并非要重写hashCode和equals

TreeMap内部存储结构是红黑树
红黑树的规则见"码出高效"
红黑树和AVL树类似。AVL树保持绝对的平衡（任意结点左右高度差不超过1），而
红黑树只保持大致的平衡。
在插入结点后，两者最多旋转两次即可达到平衡状态，而在删除时，红黑树最多三次可调至平衡，
而AVL树则要Log(N)。所以AVL树适合修改少，查询多的情况，红黑树适合修改多，查询多的情况。
注意：AVL树查询平均更快一点
put过程：先找到插入点，然后执行以下代码进行插入
Entry<K,V> e = new Entry<>(key, value, parent);
        if (cmp < 0)
            parent.left = e;
        else
            parent.right = e;
然后进行旋转着色（可能）--> 保持红黑树的结构 代码如下：
fixAfterInsertion(e);
其包含旋转着色操作，首先把当前节点置为红色，然后判断：
while (x != null && x != root && x.parent.color == RED)
如果当前节点父几点是黑色，那么只把root节点置为黑色即可，旋转着色结束。
如果当前节点父亲是红色，那么进入while循环体，进行旋转调色。
详细调整过程等闲时间再看
 */