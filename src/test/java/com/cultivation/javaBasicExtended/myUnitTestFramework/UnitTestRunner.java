package com.cultivation.javaBasicExtended.myUnitTestFramework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

class UnitTestRunner {
    UnitTestRunningResult run(Class<?> unitTestClass) throws IllegalAccessException, InstantiationException {
        // TODO: please implement the following class to run all the tests.
        // <--start
        if (unitTestClass == null) {
            throw new IllegalArgumentException();
        }

        Method[] methods = getAnnotationMyTestMethod(unitTestClass);
        ArrayList<TestResultItem> list = buildTestResultItem(unitTestClass, methods);
        return new UnitTestRunningResult(list);

        // --end-->
    }

    private ArrayList<TestResultItem> buildTestResultItem(Class<?> unitTestClass, Method[] methods) throws InstantiationException, IllegalAccessException {
        ArrayList<TestResultItem> list = new ArrayList<>();
        Object instance = unitTestClass.newInstance();

        for (Method method : methods) {
            TestResultItem resultItem = null;
            String className = unitTestClass.getName();
            String methodName = method.getName();

            try {
                method.invoke(instance);
            } catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException e) {
                String message = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
                resultItem = new TestResultItem(false, className, methodName, message);
            }

            if (Objects.isNull(resultItem)) {
                resultItem = new TestResultItem(true, className, methodName, "");
            }
            list.add(resultItem);
        }
        return list;
    }

    private Method[] getAnnotationMyTestMethod(Class<?> unitTestClass) {
        Method[] methods = unitTestClass.getDeclaredMethods();

        return Arrays.stream(methods).filter(method -> method.getAnnotation(MyTest.class) != null).toArray(Method[]::new);

    }

    // TODO: You can add additional methods if you want
    // <--start
    // --end-->
}