package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.GenericFunc;
import com.cultivation.javaBasic.util.StringFunc;
import com.cultivation.javaBasic.util.ThisInClosure;
import com.cultivation.javaBasic.util.ValueHolder;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LambdaTest {
    @Test
    void should_apply_to_interface_with_single_abstract_method() {
        StringFunc lambda = () -> "Hello";

        StringFunc lam = new StringFunc() {  //类
            @Override
            public String getString() {
                return "Hello";
            }
        };

        // () -> "Hello" 相当于，实现了StringFunc接口

        // TODO: please modify the following code to pass the test
        // <--start
        final String expect = "Hello";
        // --end-->

        assertEquals(expect, lambda.getString());
    }
    //  不能new 接口
    //  匿名类编译时产生

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_bind_to_instance_method() {
        // TODO: please bind lambda to instanceMethod.
        // <--start
        StringFunc lambda = new StringFunc() {
            @Override
            public String getString() {
                return LambdaTest.this.instanceMethod(); // 方法未调用 inner调用outter
            }
        };
        // --end-->

        assertEquals("instanceMethod", lambda.getString());  //方法调用
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_bind_to_static_method() {
        // TODO: please bind lambda to staticMethod
        // <--start
        StringFunc lambda = new StringFunc() {
            @Override
            public String getString() {
                return staticMethod();
            }
        };
        // --end-->

        assertEquals("staticMethod", lambda.getString());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_bind_to_constructor() {
        // TODO: please bind lambda to constructor of ArrayList<Integer>
        // <--start
        GenericFunc<ArrayList<Integer>> lambda = () -> new ArrayList<Integer>();
        // --end-->


        ArrayList<Integer> value = lambda.getValue();

        assertEquals(0, value.size());
    }

    @Test
    void should_capture_variable_in_a_closure() {
        int captured = 5;

        StringFunc lambda = new StringFunc() {
            @Override

            public String getString() {
                return captured + " has been captured.";   // 被捕获的变量是final，是个直接引用
            }
        };

        //闭包是：函数 + 定义的环境

        final String message = lambda.getString();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "5 has been captured.";
        // --end-->

        assertEquals(expected, message);
    }

    //在终止时捕捉变量
    @Test
    void should_in_stringfunc(){
        int captured = 5;
        StringFunc lambda = () -> captured + "has been captured.";

        Method[] declaredMethods = lambda.getClass().getDeclaredMethods();
        for(Method method :declaredMethods){
            System.out.println(method.getName());
        }

        Field[] declaredFields = lambda.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            boolean modifiers = Modifier.isFinal(field.getModifiers());
        }
    }


    @Test
    void should_evaluate_captured_variable_when_executing() {
        ValueHolder<String> value = new ValueHolder<>();
        value.setValue("I am the King of the world!");

        System.out.println(value.getValue());
        StringFunc lambda = () -> "The length of captured value is: " + value.getValue().length();

        // TODO: please write down the expected string directly.
        // <--start
        final String expected = "The length of captured value is: 4";
        // --end-->

        value.setValue("Blah");
        assertEquals(expected, lambda.getString());
    }
    //求...的值

    @Test
    void should_extend_variable_scope() {
        StringFunc stringFunc = returnLambda();
        String message = stringFunc.getString();

//        Field[] declaredFields = stringFunc.getClass().getDeclaredFields();
//        for(Field field : declaredFields){
//            System.out.println(field.getName());
//        }

        // TODO: please write down the expected string directly.
        // <--start
        final String expected = "In the year 2019";
        // --end-->

        assertEquals(expected, message);
    }

    @Test
    //this指针 —— instance
    // 闭包定义时的this，也能被保存
    void should_capture_this_variable() {
        ThisInClosure instance = new ThisInClosure();
        StringFunc stringFunc = instance.getLambda();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "ThisInClosure";
        // --end-->

        assertEquals(expected, stringFunc.getString());
    }

    private static StringFunc returnLambda() {
        int year = 2019;
        return () -> "In the year " + year;
    }

    @SuppressWarnings("unused")
    private static String staticMethod() {
        return "staticMethod";
    }

    @SuppressWarnings("unused")
    private String instanceMethod() {
        return "instanceMethod";
    }


    @Test
    void should_new_object() {
        Supplier supplier = () -> new Object();

        System.out.println(Object.class);
        System.out.println(String.class.getName());

        assertTrue(Object.class.equals(supplier.get().getClass()));
    }

    @Test
    void should_not_assign_lambda_to_object() {

    }
}

/*
 * - Do you think you can assign a lambda expression to an Object instance?
 */
