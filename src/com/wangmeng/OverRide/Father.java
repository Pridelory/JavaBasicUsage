package com.wangmeng.OverRide;

/**
 * 覆写产生的StackOverflowError
 */
public class Father {
    protected void doSomething() {
        System.out.println("Father is doing something");
        this.doSomething();
    }

    public static void main(String[] args) {
        Father father = new Son();
        father.doSomething();
    }
}

class Son extends Father {

    @Override
    public void doSomething() {
        System.out.println("Son is doing something");
        super.doSomething();
    }
}