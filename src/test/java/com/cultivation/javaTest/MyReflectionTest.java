package com.cultivation.javaTest;

import com.cultivation.javaTest.Reflection;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyReflectionTest{
    @Test
    void should_invoke_getString() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Reflection.class.getMethod("getString", String.class);
        Reflection instance = new Reflection();

        String actual = (String) method.invoke(instance, "My name");
        assertEquals("My name", actual);
    }
}