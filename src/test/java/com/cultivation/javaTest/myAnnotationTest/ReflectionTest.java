package com.cultivation.javaTest.myAnnotationTest;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

class ReflectionTest {
    @Test
    void should_define_annotation() {
        Class<MyClass> aClass = MyClass.class;

        String[] actual = Arrays.stream(aClass.getDeclaredMethods())
                .filter(method -> method.getAnnotation(MyNameAnnotation.class) != null)
                .map(Method::getName)
                .toArray(String[]::new);

        String[] expect = new String[]{"myMethod"};

        assertArrayEquals(expect, actual);
    }
}
