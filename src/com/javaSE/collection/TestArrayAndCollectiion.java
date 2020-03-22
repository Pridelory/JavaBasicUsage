//package com.javaSE.collection;
//
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * 数组和集合的互转
// */
//public class TestArrayAndCollectiion {
//
//    /**
//     * 数组转集合
//     */
//    @Test
//    public void test1() {
//        String[] stringArray = new String[3];
//        stringArray[0] = "one";
//        stringArray[1] = "two";
//        stringArray[2] = "three";
//
//        List<String> stringList = Arrays.asList(stringArray);
//        stringList.set(0, "oneList");
//        System.out.println(stringArray[0]);
//
//
//        // 以下三行能编译通过，但都出现异常，因为stringList是Arrays内部类，其不可变
////        stringList.add("four");
////        stringList.remove(2);
////        stringList.clear();
//
//        // 数组转集合标准方法
//        ArrayList<Object> objects = new ArrayList<>(Arrays.asList(stringArray));
//        // 使用java.util中的ArrayList中的add就不会出问题
//        objects.add("four");
//        objects.forEach((i) -> System.out.println(i));
//    }
//
//    /**
//     * 集合转数组
//     */
//    @Test
//    public void test2() {
//        ArrayList<String> list = new ArrayList<>(3);
//        list.add("one");
//        list.add("two");
//        list.add("three");
//
//        // 泛型丢失 无法使用String[] 接收无参方法返回的结果
//        Object[] array1 = list.toArray();
//
//        // array2数组长度小于元素个数
//        // 转换失败
//        String[] array2 = new String[2];
//        System.out.println(Arrays.asList(array2));
//
//        // 如果数组长度小于list长度 则会另起炉灶，返回一个新的数组引用
//        String[] strings = list.toArray(array2);
//        System.out.println(Arrays.asList(strings));
//
//        // array2数组长度大于元素个数
//        // 成功地把集合元素复制到array3数组中
//        String[] array3 = new String[3];
//        list.toArray(array3);
//        System.out.println(Arrays.asList(array3));
//    }
//}
//
///*
//一、数组转集合
//数组，众所周知，其有长度不可变的特性。所以在数组转集合时也应该保持这种长度不可变性，以防止很多意外操作。
//执行Arrays.asList(stringArray);会返回Arrays类下的ArrayList内部类，注意此内部类与java.util下
//的内部类不同，此Aarry下的内部类实例长度是不可变的，不能add等，只能改变其中的元素。因为此"李鬼"ArrayList
//用的是适配器设计模式，实际上里面用的还是指向原数组的变量，所以上述不能add只能set的操作便合理了。
//
//如果想用正常地即java.util下的ArrayList相关操作，在外面包一层即可，如下:
//ArrayList<Object> objectList = new ArrayList<>(Arrays.asList(stringArray));
//这个就是数组转集合的标准做法，需牢记
//
//二、集合转数组
//如test2所示，不要用无参的toArray()把集合转数组，这样会使泛型丢失。如果即将复制进去的数组容量
//足够，直接复制。容量不够，另起炉灶，这时只能再获取返回一个新的数组引用。源码如下
//
//public <T> T[] toArray(T[] a) {
//        // 数组小于集合size的情况
//        if (a.length < size)
//            // Make a new array of a's runtime type, but my contents:
//            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
//        // 不然直接复制
//        System.arraycopy(elementData, 0, a, 0, size);
//        if (a.length > size)
//            a[size] = null;
//        return a;
//    }
//
//根据测试，得出结论：
//使用集合toArray(T[] array)方法转换为数组时，注意需要传入类型完全一样的数组，并且其容量大小为list.size() 性能最优！
// */
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
