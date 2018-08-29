package com.cultivation.javaTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CloneTest {

    @Test
    void should_test_clone_int() {
        final int[] array = {1, 2};
        final int[] storage = array.clone();

        assertTrue(array[0] == storage[0]);
        assertSame(array[0], storage[0]);
        // 参数是Object int会转为Integer 所以比较的是经过转换的
        assertFalse(array == storage);
    }

    @Test
    void should_test_clone_string() {
        final String[] array = {"1", "2"};
        final String[] storage = array.clone();

        assertSame(array[0], storage[0]);
        assertFalse(array == storage);
    }
}