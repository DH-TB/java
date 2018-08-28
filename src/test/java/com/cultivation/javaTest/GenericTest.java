package com.cultivation.javaTest;

import java.util.ArrayList;
import java.util.List;

public class GenericTest {

    public static void main(String[] args) {
        printMsg(1,2,3);
    }

    public static <T> void printMsg( T... args){
        for(T t : args){
            System.out.println(t);
        }
    }
}
