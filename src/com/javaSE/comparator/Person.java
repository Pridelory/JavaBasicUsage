package com.javaSE.comparator;


/**
 * Person类
 */
class Person implements Comparable<Person> {
    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 钱
     */
    private Integer money;

    public Person(String name, Integer age, Integer money) {
        this.name = name;
        this.age = age;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public int compareTo(Person p) {
        if (this.age != p.age) {
            return this.age > p.age ? 1 : -1;
        }
        if (this.money != p.money) {
            return this.money.intValue() > p.money.intValue() ? 1 : -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }
}
