package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.Employee;
import com.cultivation.javaBasic.util.MethodWithAnnotation;
import com.cultivation.javaBasic.util.MyAnnotation;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

//4
class ReflectionTest {
    @Test
    void should_be_able_to_get_class_object() {
        Employee employee = new Employee();
        Class<? extends Employee> employeeClass = employee.getClass();

        // TODO: please modify the following code to pass the test
        // <--start
        final Class<? extends Employee> expected = Employee.class;
        // --end-->

        assertEquals(expected, employeeClass);
    }

    @Test
    void should_be_able_to_get_full_qualified_name() {
        Employee employee = new Employee();
        Class<? extends Employee> employeeClass = employee.getClass();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "com.cultivation.javaBasic.util.Employee";
        // --end-->

        assertEquals(expected, employeeClass.getName());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_instantiate_types_at_runtime() throws Exception {
        Class<?> aClass = Class.forName("com.cultivation.javaBasic.util.Employee");

        // TODO: please created an instance described by `theClass`

        // <--start
        Employee instance = (Employee) aClass.newInstance();
        // --end-->

        assertEquals("Employee", instance.getTitle());
    }

//   在运行时实例化类型
//   https://docs.oracle.com/javase/tutorial/reflect/class/classNew.html
//   https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Method.html#getDeclaringClass--

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_print_all_static_methods_of_double() {
        Class<Double> doubleClass = Double.class;

        // TODO: please get all public static declared methods of Double. Sorted in an ascending order
        // <--start

        Method[] declaredMethods = doubleClass.getDeclaredMethods();


        List<String> methodList = new ArrayList<>();
        for (Method method : declaredMethods) {
            if (Modifier.isStatic(method.getModifiers()) && Modifier.isPublic(method.getModifiers())) {
               methodList.add(method.getName());
            }
        }
        Collections.sort(methodList);
        String[] publicStaticMethods = methodList.toArray(new String[0]);

        // --end-->

        final String[] expected = {
                "compare", "doubleToLongBits", "doubleToRawLongBits", "hashCode",
                "isFinite", "isInfinite", "isNaN", "longBitsToDouble", "max",
                "min", "parseDouble", "sum", "toHexString", "toString", "valueOf",
                "valueOf"
        };

        assertArrayEquals(expected, publicStaticMethods);
    }
//  https://stackoverflow.com/questions/287645/how-can-i-check-if-a-method-is-static-using-reflection/287654


    @SuppressWarnings({"unused", "ConstantConditions", "RedundantThrows"})
    @Test
    void should_be_able_to_evaluate_object_field_values_at_runtime() throws Exception {
        Object employee = new Employee();

        // TODO: please get the value of `getTitle` method using reflection. No casting to Employee is allowed.
        // <--start
        Method getTitle = employee.getClass().getMethod("getTitle");
        Object result = getTitle.invoke(employee);
        // --end-->

        assertEquals("Employee", result);
    }

//    https://stackoverflow.com/questions/796312/java-question-how-to-get-the-value-of-a-method-from-an-unknown-object
//    https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Method.html#invoke-java.lang.Object-java.lang.Object...-

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_be_able_to_get_the_item_class_of_the_array() {
        Object employees = new Employee[0];

        // TODO: please get the class of array item `employees`
        // <--start
        Class<?> itemClass = employees.getClass().getComponentType();
        // --end-->

        assertEquals(Employee.class, itemClass);
    }

//    https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html#getComponentType--


    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_get_the_methods_who_contains_MyAnnotation_annotation() {
        Class<MethodWithAnnotation> theClass = MethodWithAnnotation.class;

        // TODO: please get the methods who contains MyAnnotation annotation.
        // <--start

        Method[] methods = theClass.getMethods();

//        String[] methodsContainsAnnotations = null;

        List<String> methodList = new ArrayList<>();
        for (Method method : methods) {
            if (method.getAnnotation(MyAnnotation.class) != null) {
                methodList.add(method.getName());
            }
        }
        String[] methodsContainsAnnotations = methodList.toArray(new String[0]);

        assertArrayEquals(new String[]{"theMethod"}, methodsContainsAnnotations);
    }
}
//      Returns this element's annotation for the specified type if such an annotation is present, else null.
//   https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Method.html#getAnnotation-java.lang.Class-
//   return array  public methods of the class or interface

/*
 * - What is the class name of array type?
 * - How to compare 2 classes?
 * - What if the class does not contain a default constructor when you call `newInstance()`?
 * - What is source only annotation? Can we get source only annotations via reflection?
 */
