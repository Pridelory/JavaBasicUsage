package com.javaSE.keywords.finalkeywords;

public class TestFinal {
    public static void main(String[] args) {

        final Person person = new Person();
        // 成员变量用final修饰 其引用就变不了了
//        person = new Person();

        Dog dog = new Dog();
        dog.breathe();
        System.out.println(dog.name);

        // 测试String
        String s = "111";
        System.out.println(s.hashCode());
        s += "222";
        System.out.println(s.hashCode());
        // 以上说明s的引用变了
        // 以下说明"111"在内存常量池中只有一份
        String a = "111";
        System.out.println(a.hashCode());
    }


}
