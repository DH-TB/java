package com.cultivation.javaTest.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

class   MyReflectionTest{
    @Test
    void should_invoke_getString() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Reflection.class.getMethod("getString", String.class);
        String string = (String) method.invoke(new Reflection(), "string");
        assertEquals("string", string);
    }
}