package com.cultivation.javaBasic;

import com.cultivation.javaBasic.showYourIntelligence.StackFrameHelper;
import com.cultivation.javaBasic.util.ClosableStateReference;
import com.cultivation.javaBasic.util.ClosableWithException;
import com.cultivation.javaBasic.util.ClosableWithoutException;
import com.cultivation.javaBasic.util.MyClosableType;
import com.cultivation.javaBasic.showYourIntelligence.StringFormatException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//
class ExceptionTest {
    @Test
    void should_customize_exception() {
        try {
            throw new StringFormatException("message");
        } catch (StringFormatException error) {

            assertEquals("message", error.getMessage());
            assertNull(error.getCause());
        }
    }
    //定制

    @Test
    void should_customize_exception_continued() {
        Exception innerError = new Exception("inner error");

        Field[] declaredFields = Throwable.class.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
        }

        try {
            throw new StringFormatException("the message", innerError);
        } catch (StringFormatException error) {
            assertEquals("the message", error.getMessage());
            assertEquals(innerError, error.getCause());
        }
    }

    @Test
    void should_be_careful_of_the_order_of_finally_block() {
        int confusedResult = confuse(2);

        // TODO: please modify the following code to pass the test
        // <--start
        final int expectedResult = 0;
        // --end-->

        assertEquals(expectedResult, confusedResult);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_use_the_try_pattern() {
        ClosableStateReference closableStateReference = new ClosableStateReference();
        MyClosableType closableType = null;
        try (MyClosableType closable = new MyClosableType(closableStateReference)) {
            closableType = closable;
            assertFalse(closable.isClosed());

            throw new RuntimeException();
        } catch (RuntimeException e) {
            assertTrue(closableType.isClosed());
        }


        //在这里调用close()方法

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expected = Optional.of(true);
        // --end-->

        assertEquals(expected.get(), closableStateReference.isClosed());
    }

    @SuppressWarnings({"EmptyTryBlock", "unused"})
    @Test
    void should_call_closing_even_if_exception_throws() throws Exception {
        ArrayList<String> logger = new ArrayList<>();

        try {
            try (AutoCloseable withoutThrow = new ClosableWithoutException(logger);
                 AutoCloseable withThrow = new ClosableWithoutException(logger)) {
            }
        } catch (Exception e) {
            // It is okay!
        }

        // TODO: please modify the following code to pass the test
        // <--start
        final String[] expected = { "ClosableWithoutException.close"};
        // --end-->

        assertArrayEquals(
                expected,
                logger.toArray());
    }

    // 只会关闭非空资源
//    在离开try块时将自动调用close()方法。该方法调用可以看做在finally块中，
//    try括号内的资源会在try语句结束后自动释放，前提是这些可关闭的资源必须实现 java.lang.AutoCloseable 接口。

    @Test
    void should_get_method_name_in_stack_frame() {
        String methodName = StackFrameHelper.getCurrentMethodName();

        assertEquals(
                "com.cultivation.javaBasic.ExceptionTest.should_get_method_name_in_stack_frame",
                methodName);
    }

    @Test
    void methodA()  {
        methodB();
        assertTrue(true);
    }
    void methodB(){

    }

    @SuppressWarnings({"ReturnInsideFinallyBlock", "SameParameterValue"})
    private int confuse(int value) {
        testTryOrder();
        try {
            return value * value;
        } finally {
            if (value == 2) {
                return 0;
            }
        }
    }


    void testTryOrder() {
        try {
            System.out.println("try");
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("catch");
        } finally {
            System.out.println("finally");
        }
    }
}


/*
 * - Please draw the hibachi of `Throwable` and explain the main purpose for each type.
 * - When do you have to declare a exception in the method signature.
 * - When you declare a class A in package PA, and A contains a method
 *   `callMeToDeath() throw FileNotFoundException`. Package PB imports PA and uses
 *   `PA.A.callMeToDeath()`. Both PA and PB are built and deployed. Now PA is updated and
 *   the method `callMeToDeath()` throws another exception. Can we just build and replace
 *   PA?
 * - Can you declare a method throws unchecked exceptions?
 * - If a super class method throws no checked exception, could a derived class override its
 *   method and throw checked exceptions?
 * - Which constructor do you want to implement if you provide your own exception.
 */
