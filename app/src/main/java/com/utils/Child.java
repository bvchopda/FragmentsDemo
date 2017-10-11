package com.utils;

/**
 * Created by cygnet on 14/7/17.
 */

public class Child {
    String name;
    int age;

    public static class Builder {
        Child child;

        public Builder()
        {
            child = new Child();
        }

        public Builder setName(String name)
        {
            child.name = name;
            return this;
        }

        public Builder setAge(int age)
        {
            child.age = age;
            return this;
        }

        public Child build()
        {
            return child;
        }
    }
}
