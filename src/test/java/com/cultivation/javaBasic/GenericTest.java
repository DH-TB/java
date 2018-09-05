package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.Employee;
import com.cultivation.javaBasic.util.KeyValuePair;
import com.cultivation.javaBasic.util.Manager;
import com.cultivation.javaBasic.util.Pair;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//
class GenericTest {
    @SuppressWarnings("unused")
    @Test
    void should_auto_resolve_generic_method() {
        final String[] words = {"Hello", "Good", "Morning"};

        // TODO: please call getMiddle method for string
        // <--start
        final String middle = getMiddle(words);


        // --end-->

        assertEquals("Good", middle);
    }


    @Test
    void should_specify_a_type_restriction_on_typed_parameters() {
        int minimumInteger = min(new Integer[]{1, 2, 3});
        double minimumReal = min(new Double[]{1.2, 2.2, -1d});

        assertEquals(1, minimumInteger);
        assertEquals(-1d, minimumReal, 1.0E-05);
    }

    //约束

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_not_know_generic_type_parameters_at_runtime() {
        KeyValuePair<String, Integer> pair = new KeyValuePair<>("name", 2);
        KeyValuePair<Integer, String> pairWithDifferentTypeParameter = new KeyValuePair<>(2, "name");


        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expected = Optional.of(true);
        // --end-->
        assertEquals(expected.get(), pair.getClass().equals(pairWithDifferentTypeParameter.getClass()));
    }

    @Test
    void should_can_transfer_type() {
        KeyValuePair<String, Integer> pair = new KeyValuePair<>("name", 2);

        KeyValuePair keyValuePair = new KeyValuePair<>(new Object(), new Object());
        KeyValuePair<String, Integer> havePair = keyValuePair;

    }

    // class com.cultivation.javaBasic.util.KeyValuePair

    @SuppressWarnings({"UnnecessaryLocalVariable", "unchecked", "unused", "ConstantConditions"})
    @Test
    void should_be_careful_of_raw_type_generic() {
        Pair<Manager> managerPair = new Pair<>();
        Pair rawPair = managerPair;
        rawPair.setFirst(new Employee());

        boolean willThrow = false;
        try {
            Manager first = managerPair.getFirst();
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
    private static <T> T getMiddle(T[] args) {
        return args[args.length / 2];
    }

    // TODO: please implement the following code to pass the test. It should be generic after all.
    // The method should only accept `Number` and the number should implement `Comparable<T>`
    // <--start
    @SuppressWarnings("unused")
    private static <T extends Number & Comparable<T>> T min(T[] values) {
        T min = values[0];
        for (T value : values) {
            if (min.compareTo(value) > 0) {
                min = value;
            }
        }
        return min;
    }
    // --end-->

    // TODO: please implement following method to pass the test. But you cannot change the signature
    // <--start
    @SuppressWarnings("unused")
    private static void swap(Pair<?> pair) {
        swapWithGeneric(pair);
    }

    private static <T> void swapWithGeneric(Pair<T> pair) {
        T first = pair.getFirst();
        T second = pair.getSecond();

        pair.setFirst(second);
        pair.setSecond(first);
    }

    private static void genericNumber(Pair<? super Integer> pair) {
        ArrayList<Integer> integers = new ArrayList<>();
        ArrayList<? extends Number> integers1 = integers;
    }

    private static <T> ArrayList getArrayList() {
        ArrayList<T> list = new ArrayList<>();
        return list;
    }

    private static <T> ArrayList getArrayListWithParameter() {
        ArrayList<T> list = new ArrayList<>();
        return list;
    }
    // TODO: You can add additional method within the range if you like
    // <--start

    // --end-->

    <T> T[] toArray(int length, Generic<T> generic) {
        return generic.returnArray(length);

    }

    @Test
    void should_return_array() {
        String[] stringArray = toArray(5, (length) -> new String[length]);

        assertEquals(5, stringArray.length);
//
        assertEquals(stringArray.getClass().getComponentType(), String.class);
    }
}

interface Generic<T> {
    T[] returnArray(int length);
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

