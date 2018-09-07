package com.cultivation.javaTest;

public class SubClass extends ParentClass {

    public static void main(String[] args) {
        SubClass.getString();
        ParentClass.getString();

        new ParentClass().test();
        new SubClass().test();
    }
}