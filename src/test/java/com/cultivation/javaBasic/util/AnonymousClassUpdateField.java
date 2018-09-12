package com.cultivation.javaBasic.util;

public class AnonymousClassUpdateField {
    private int year;

    public AnonymousClassUpdateField() {
        year = 2018;
    }

    public int getYear() {
        return year;
    }

    @SuppressWarnings("Convert2Lambda")
    public void somethingHappen() {
        Runnable runnable = new Runnable(){

            @Override
            public void run(){
                ++year;
            }

        };
        runnable.run();

        Employee employee = new Employee() {
        };
        employee.getTitle();

    }

}

