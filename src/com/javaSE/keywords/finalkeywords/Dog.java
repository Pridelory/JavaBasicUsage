package com.javaSE.keywords.finalkeywords;

public class Dog extends Animal{

    // final 修饰的成员变量表示常量，只能被赋值一次赋值后不再改变
    public final String name = "皮皮";


    // 可见用final修饰的方法不能覆盖
    // 这个方法所属类可以被继承
//    public void breathe() {
//        System.out.println("breathing............");
//    }


    public String getName() {
        return name;
    }
}
