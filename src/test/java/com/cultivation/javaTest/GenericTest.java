package com.cultivation.javaTest;

public class GenericTest {

    public static void main(String[] args) {
        printMsg(1,2,3);
    }

    private static <T> void printMsg(T... args){
        for(T t : args){
            System.out.println(t);
        }
    }
}
