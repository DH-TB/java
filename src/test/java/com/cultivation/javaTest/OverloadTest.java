package com.cultivation.javaTest;

public class OverloadTest {

    public static void main(String[] args) {

        short shortValue = 5;
        overloadMethod(shortValue);

        int intValue = 10;
        overloadMethod(intValue);

        overloadMethod(shortValue, shortValue);

        Short ShortValue = 5;
        overloadMethod(ShortValue);

        overloadMethod(1,2);

    }

    public static void overloadMethod(int value) {
        System.out.println("调用  overloadMethod(int)");
    }

    public static void overloadMethod(short value) {
        System.out.println("调用  overloadMethod(short)");
    }

    public static void overloadMethod(Short value) {
        System.out.println("调用  overloadMethod(Short)");
    }

    public static void overloadMethod(short... args) {
        System.out.println("调用  overloadMethod(short...)");
    }


    public static void overloadMethod(Short... args) {
        System.out.println("调用  overloadMethod(Short...)");
    }

//    public static void overloadMethod(Integer... args) {
//        System.out.println("调用  overloadMethod(Integer...)");
//    }

      public static void overloadMethod(Integer value) {
        System.out.println("调用  overloadMethod(Integer)");
    }

    public static void overloadMethod(int... value) {
        System.out.println("调用  overloadMethod(int...)");
    }

//    public static void overloadMethod(int arg1, int arg2) {
//        System.out.println("调用  overloadMethod(int,int)");
//    }

    public static void overloadMethod(short arg1, short arg2) {
        System.out.println("调用  overloadMethod(short,short)");
    }

    public static void overloadMethod(Short arg1, Short arg2) {
        System.out.println("调用  overloadMethod(Short,Short)");
    }
}

//case1: 对于short类型，匹配顺序 short > int > Short > (short... > Short...)
//case2: int > Ingeter
//case3: short short > int int > Short Short > short... > Short...
//case4: Short > short > int >