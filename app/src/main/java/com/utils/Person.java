package com.utils;

/**
 * Created by cygnet on 14/7/17.
 */

public class Person {
    String name;
    int age;

    public static Person getInstance() {
        return new Person();
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }
}
