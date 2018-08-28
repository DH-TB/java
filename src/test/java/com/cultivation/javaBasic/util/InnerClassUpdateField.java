package com.cultivation.javaBasic.util;

public class InnerClassUpdateField {
    private int year;

    public InnerClassUpdateField(){
        year = 2018;
    }

    public void somethingHappen(){
        this.new YearIncrementer().increment();
    }

    public int getYear(){
        return this.year;
    }

    public class YearIncrementer{
        public void increment(){
            ++InnerClassUpdateField.this.year;
        }
    }
}
