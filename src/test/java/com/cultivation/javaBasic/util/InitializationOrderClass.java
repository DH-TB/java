package com.cultivation.javaBasic.util;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class InitializationOrderClass {
    private static List<String> logger = new ArrayList<>();

    public static void resetLogs() {
        logger.clear();
    }

    public static String[] getLogs() {
        return logger.toArray(new String[0]);
    }
//    该方法返回集合中所有元素的数组；返回数组的运行时类型与指定数组的运行时类型相同
//    但是使用无参数的toArray()有一个缺点，就是转换后的数组类型是Object[]
//    https://blog.csdn.net/u012260238/article/details/79699318
//  https://blog.csdn.net/qq496013218/article/details/52840840

    {
        logger.add("Initialization Block");
    }

    private final Object field = initField();

    private Object initField() {
        logger.add("Field Initializer");
        return null;
    }

    public InitializationOrderClass() {
        this(4);
        logger.add("Default constructor");
    }

    public InitializationOrderClass(int argument) {
        logger.add("Constructor with argument");
    }

    {
        logger.add("Initialization Block another");
    }
    private final Object fieldAnother = initAnotherField();

    private Object initAnotherField() {
        logger.add("Field Initializer another");
        return null;
    }


}
