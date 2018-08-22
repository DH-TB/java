package com.cultivation.javaBasic;

public class Reflection{
    private String name = "11";

    public Reflection(){
    }

    public Reflection(String name) {
        this.name = name;
    }

    public String getString(){
        return this.name;
    }

    public String getString(String name){
        return name;
    }
}