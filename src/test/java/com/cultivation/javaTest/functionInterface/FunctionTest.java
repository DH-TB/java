package com.cultivation.javaTest.functionInterface;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.function.Function;

import static com.cultivation.javaBasicExtended.myUnitTestFramework.MyAssert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

class FunctionTest {

    @Test
    void should_test_int_supplier() {
        IntSupplier lambda = () -> 1;
        int actual = lambda.getAsInt();

        assertEquals(1, actual);
    }

    @Test
    void should_test_char_supplier() {
        CharSupplier lambda = () -> 'a';
        char actual = lambda.getAsChar();

        assertEquals('a', actual);
    }

    @Test
    void should_test_int_function() {
        IntFunction lambda = (value) -> value;
        int actual = lambda.apply(5);

        assertEquals(5, actual);
    }

    @Test
    void should_test_int_BiFunction() {
        IntBiFunction lambda = (value1, value2) -> value1 + value2;
        int actual = lambda.apply(1, 1);

        assertEquals(2, actual);
    }

    private void test(Object[] array) {
        if(array.length != 1) {
            Object num1 = array[0];
            Object num2 = array[1];
            array[0] = num2;
            array[1] = num1;
        }
    }

    @Test
    void should_test_consumer_1() {
        Object[] intArray = {1};
        Consumer lambda = this::test;

        lambda.accept(intArray);

        assertArrayEquals(new Object[]{1}, intArray);
    }

    @Test
    void should_test_consumer_2() {
        Object[] array = {1, 2};
        Consumer lambda = this::test;

        lambda.accept(array);

        assertArrayEquals(new Object[]{2, 1}, array);
    }

    @Test
    void should_test_consumer_3() {
        Object[] intArray = {1, 2, 3};
        Consumer lambda = array -> test(array);

        lambda.accept(intArray);
        assertArrayEquals(new Object[]{2, 1, 3}, intArray);
    }

    private int sum(int[] array){
        if(array == null) return 0;
        return Arrays.stream(array).reduce((num1, num2) -> num1 + num2).orElse(0);
    }

    @Test
    void should_test_sum_function_1() {
        int[] intArray = null;
        SumFunction lambda = this::sum;
        int actual = lambda.apply(intArray);

        assertEquals(0, actual);
    }

    @Test
    void should_test_sum_function_2() {
        int[] intArray = {1};
        SumFunction lambda = this::sum;
        int actual = lambda.apply(intArray);

        assertEquals(1, actual);
    }


    @Test
    void should_test_sum_function_3() {
        int[] intArray = {1, 2, 3, 4, 5};

        SumFunction lambda = this::sum;
        int actual = lambda.apply(intArray);

        assertEquals(15, actual);
    }
}
