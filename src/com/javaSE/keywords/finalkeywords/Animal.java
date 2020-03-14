package com.javaSE.keywords.finalkeywords;

/**
 * 动物类
 * 演示final关键字作用在方法上
 */
public class Animal {

    private Double height;

    private Double weight;

    public final void breathe() {
        System.out.println("breathing............");
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
