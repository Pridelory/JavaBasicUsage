package com.javaSE.collection;

import java.util.ArrayList;

/**
 * ArrayList扩容过程（详解）
 */
public class TestArrayList {
    public static void main(String[] args) {
        new ArrayList<>();
    }
}

/*
一、ArrayList一些Fields详解
1、elementData：这是ArrayList真正用于存储元素的数组，elementData.size。
2、size：elementData真正存储的元素个数,注意elementData.length与size的区别，前者是数组长度，后者为改数组内的元素的个数（其余为default）
3、EMPTY_ELEMENTDATA：给ArrayList初始化传的长度是0的时候给elementData初始化的数组
4、DEFAULTCAPACITY_EMPTY_ELEMENTDATA：调用ArrayList无参构造器时给elementData初始化的数组，其与EMPTY_ELEMENTDATA的区别是：
"We distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when first element is added"
5、DEFAULT_CAPACITY：当调用ArrayList无参构造器后，第一次向该集合添加元素时该集合扩展至的容量，也就是数组长度，缺省为10

二、扩容过程（无参）
调用ArrayList无参构造器，执行this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA; 随后执行add方法：
public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }
在执行elementData[size++] = e;之前要给容器扩容，防止数组越界。最小扩容后的长度为size + 1,这个是最起码的底线，也就是minCapacity

private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

可以看到当elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA时，表名调用了无参构造器，此时直接返回DEFAULT_CAPACITY
否则，返回minCapacity

private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
        // 如果最小的底线长度大于此时数组长度时，才需要扩容
            grow(minCapacity);
    }

private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1); // 扩容过程
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity); // 重新复制了一份数组
    }

至此ArrayList扩容完毕

三、扩容过程（有参）
调用ArrayList有参构造函数传进去期望容器长度，会执行 this.elementData = new Object[initialCapacity];（initialCapacity>0），
一步到位。由二可知调用无参构造函数添加到1000个元素时，容器要扩容13次，耗费资源。耗费资源，所以在使用ArrayList时候，最好提前进行预估，
再调用有参构造函数。

四、其他
1、为什么elementData要用transient修饰？
答：用transient修饰的成员变量（非static）在序列化/反序列化时会被忽略。这里是因为elementData或许会有一部分是空元素，没必要序列化。
而且ArrayList提供了writeObject和readObject来进行关键元素的序列化和反序列化（反序列化后把元素再还原到elementData），所以elementData
没必要再进行序列化。

 */
