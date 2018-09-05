package com.cultivation.javaBasic;

import com.cultivation.javaBasic.showYourIntelligence.MyStack;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ArrayTest {
    @Test
    void should_resize_array() {
        final int itemsCount = 25;
        final int initialCapacity = 10;
        //初始 容量

        MyStack myStack = new MyStack(initialCapacity);
        for (int i = 0; i < itemsCount; ++i) {
            myStack.push(i);
        }

        int[] array = myStack.popToArray();

        assertArrayEquals(
                new int[]{24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0},
                array
        );
    }

    @Test
    void should_change_array_size(){
        int[] intArray = new int[10];
//        intArray.length = 11;
        intArray = new int[11];
    }
}


