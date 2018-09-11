package com.cultivation.javaBasic.util;

public class InnerClassUpdateField {
    private int year;

    public InnerClassUpdateField() {
        this.year = 2018;
    }

    public void somethingHappen(){
        this.new InnerClass().increment();
    }

    public int getYear() {
        return year;
    }


    public void add(int year){

        class InnerClass{
            static final int year = 2;
        }

        this.new InnerClass(year).add();
    }

    public class InnerClass {
        private int year;

        static final int age = 2;

        public void add() {
            InnerClassUpdateField.this.year += this.year;
        }

        public InnerClass(int year) {
            this.year = year;
        }

        public InnerClass() {

        }

        public void increment() {
            InnerClassUpdateField.this.year += 2;
        }
    }
}
