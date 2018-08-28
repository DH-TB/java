package com.cultivation.javaBasic.util;

public class LocalClassUpdateField {
    private int year;

    public LocalClassUpdateField() {
        year = 2018;
    }

    public int getYear() {
        return year;
    }

    public void somethingHappen() {
        class YearIncrementer {
            @SuppressWarnings("WeakerAccess")
            public YearIncrementer(){
                System.out.println("test");
            }
            public void increment() {
                ++year;
            }
        }
        System.out.println("test1");
        new YearIncrementer().increment();
        System.out.println("test2");

    }
}