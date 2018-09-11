package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.Employee;
import com.cultivation.javaBasic.util.KeyValuePair;
import com.cultivation.javaBasic.util.Manager;
import com.cultivation.javaBasic.util.Pair;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//
public class GenericTest {
    @SuppressWarnings("unused")
    @Test
    void should_auto_resolve_generic_method() {
        final String[] words = {"Hello", "Good", "Morning"};
        final String middle = getLast(words);

        final Integer[] intArray = {1, 2, 3};
        final int last = getLast(intArray);
        assertEquals(3, last);
        assertEquals("Morning", middle);
    }


    @Test
    void should_specify_a_type_restriction_on_typed_parameters() {
        int minimumInteger = min(new Integer[]{1, 2, 3});
        double minimumReal = min(new Double[]{1.2, 2.2, -1d});
        String minimunString = min(new String[]{"a", "b"});

        assertEquals("a", minimunString);
        assertEquals(1, minimumInteger);
        assertEquals(-1d, minimumReal, 1.0E-05);
    }

    //约束

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_not_know_generic_type_parameters_at_runtime() {
        KeyValuePair<String, Integer> pair = new KeyValuePair<>("name", 2);
        KeyValuePair<Integer, String> pair1 = new KeyValuePair<>(2, "name");

        final Optional<?> expect = Optional.of(true);
        assertEquals(expect.get(), pair.getClass().equals(pair1.getClass()));
    }

    @SuppressWarnings({"UnnecessaryLocalVariable", "unchecked", "unused", "ConstantConditions"})
    @Test
    void should_be_careful_of_raw_type_generic() {
        Pair<Manager> managerPair = new Pair<>();
        Pair rawPair = managerPair;
        rawPair.setFirst(new Employee());  // 类型推断

        //row type大的 set 具体的 get
        Pair<Employee> employeePair = new Pair<>();
        employeePair.setFirst(new Manager());       // manager是子类，可以隐示转换为父类
        //父类里面可以 set 子类，子类不可以 set 父类


        boolean willThrow = false;
        try {
            Manager first = managerPair.getFirst();   //get 类型
        } catch (ClassCastException error) {
            willThrow = true;
        }

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expected = Optional.of(true);
        // --end-->

        assertEquals(expected.get(), willThrow);
    }

    @Test
    void should_swap() {
        Pair<String> pair = new Pair<>("Hello", "World");

        swap(pair);

        assertEquals("World", pair.getFirst());
        assertEquals("Hello", pair.getSecond());
    }

    @SuppressWarnings("unused")

    private static <T> T getLast(T[] array) {
        return array[array.length - 1];
    }

    // TODO: please implement the following code to pass the test. It should be generic after all.
    // The method should only accept `Number` and the number should implement `Comparable<T>`
    // <--start
    @SuppressWarnings("unused")
    private static <T extends Comparable<T>> T min(T[] array) {
        T minValue = array[0];
        for (T item : array) {
            if (item.compareTo(minValue) < 0)
                minValue = item;
        }
        return minValue;
    }




    private static void swap(Pair<?> pair) {
        swapWithGeneric(pair);
    }

    private static <T> void swapWithGeneric(Pair<T> pair) {
        T first = pair.getFirst();
        T second = pair.getSecond();

        pair.setFirst(second);
        pair.setSecond(first);
    }






    // --end-->

    // TODO: please implement following method to pass the test. But you cannot change the signature
    // <--start
    @SuppressWarnings("unused")
    @Test
    void should_abrase_type_is_object_when_not_bound_in_generic() throws NoSuchFieldException {
        MyClass<String> stringMyClass = new MyClass<>();
        Field field = stringMyClass.getClass().getDeclaredField("field");

        Class<Object> expected = Object.class;

        assertEquals(expected, field.getType());
    }

    class MyClass<T>{
        private T field;
    }


    @Test
    void should_can_transfer_type() {
        KeyValuePair<String, Integer> pair = new KeyValuePair<>("name", 2);
        KeyValuePair keyValuePair = new KeyValuePair<>(new Object(), new Object());
        KeyValuePair<String, Integer> havePair = keyValuePair;
    }


    @Test
    void should_test_sub_class_can_generic() {
        String[] array = {"1", "2"};
        MySubClass subClass = new MySubClass(array);

        Object[] array1 = subClass.getArray();
//        String string = testSubClass(new String[]{"1", "2"});
//        assertEquals("1", string);

    }


    class MyParentClass<T> implements Comparable<T>{

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    class MySubClass<T> extends MyParentClass<T>{
        private T[] array;

        MySubClass(T[] array) {
            this.array = array;
        }

        public T[] getArray() {
            return array;
        }
    }

    private static <T extends MyParentClass<T>> T testSubClass(T[] array){

        T minValue = array[0];
        for (T item : array) {
            if (item.compareTo(minValue) < 0)
                minValue = item;
        }
        return minValue;
    }
}


/*
 * - Can you give an example when you have to specify a typed parameter in generic method call?
 * - Can type parameter have more than one bounds? Can type parameter bounds to class? Can type parameter bounds to both
 *   class and interface?
 * - What if you have 2 class that one is generic called `KeyValuePair<K, V>` and the second is a non-generic method
 *   called `KeyValuePair` in the same package?
 * - Can you use primitive types as type parameter?
 * - What is the result of the following code
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   ```
 * - What is the result of the following code
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   KeyValuePair<String, Integer> value = keyValuePairs[1];
 *   ```
 * - What is the result of the following code
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   KeyValuePair<String, Integer> value = keyValuePairs[1];
 *   String key = value.getKey();
 *   ```
 * - Please describe the wildcard generic type.
 */

