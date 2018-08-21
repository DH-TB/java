package com.cultivation.javaBasic.showYourIntelligence;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class MyStack {
    private int[] storage;
    private int capacity;
    private int count;
    private static final int GROW_FACTOR = 2;
    // 增长因子

    public MyStack(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Capacity too small.");
        }

        capacity = initialCapacity;
        storage = new int[initialCapacity];
        count = 0;
    }

    public void push(int value) {
        if (count == capacity) {
            ensureCapacity();
        }
        // 保证容量

        storage[count++] = value;
//      System.out.println(Arrays.toString(storage));



        // TODO: Please push the value into the storage here.

        // <--start
        // --end-->
    }

    private void ensureCapacity() {
        int newCapacity = capacity * GROW_FACTOR;

        capacity = newCapacity;
        int[] newStorage = new int[newCapacity];

        for (int i = 0; i < storage.length; i++) {
            newStorage[i] = storage[i];
        }
        // 堆栈
        storage = newStorage;

        // TODO: Please create a new array of size newCapacity. And update related fields
        // TODO: You SHOULD NOT USE COLLECTIONS OTHER THAN ARRAY.
        // <--start
        // --end-->
    }

    public int[] popToArray() {

        final int totalItemsCount = count;
        int[] array = new int[totalItemsCount];

        while (count > 0) {
            array[totalItemsCount - count] = pop();
        }

        return array;
    }

    private int pop() {
        return storage[--count];
        // TODO: Please pop one element from the array.
        // <--start
        // --end-->

//        throw new UnsupportedOperationException("Stack is empty.");
    }
}
