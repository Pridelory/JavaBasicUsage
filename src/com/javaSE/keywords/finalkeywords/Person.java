package com.javaSE.keywords.finalkeywords;

/**
 * final作用在类上表明这个类不能被继承
 */
public final class Person {

    private String name;

    private Integer age;

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
}
