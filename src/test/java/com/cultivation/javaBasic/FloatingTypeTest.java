package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FloatingTypeTest {
    @Test
    void should_not_get_rounded_result_if_convert_floating_number_to_integer() {
        final float floatingPointNumber = 2.75f;
        final Double integer = (double) floatingPointNumber;

        // TODO: Please change the result to pass the test.
        // <!--start
        double a = Math.pow(2, 30);
        final Double expected = 2.75;
        // --end-->

        assertEquals(expected, integer);
    }

    @Test
    void should_transfer() {
        int actual = 1;
        byte expect = (byte) actual;
        assertEquals(expect, actual);
    }
    // 圆 循环
    //   值为 2^31－1 的常量，它表示 int 类型能够表示的最大值。
    //https://doc.yonyoucloud.com/doc/jdk6-api-zh/java/lang/Integer.html

    @SuppressWarnings({"divzero", "NumericOverflow"})
    @Test
    void should_judge_special_double_cases() {
        assertTrue(isInfinity(1d / 0d));
        assertTrue(isInfinity(-1d / 0d));
        assertFalse(isInfinity(2d));
        assertFalse(isInfinity(Double.NaN));

        assertTrue(isNan(0d / 0d));
        assertFalse(isNan(Double.NEGATIVE_INFINITY));
        assertFalse(isNan(Double.POSITIVE_INFINITY));
    }
    //https://www.cnblogs.com/zhisuoyu/p/5314541.html

    @Test
    void should() {

        assertEquals(Double.NaN, Double.NaN);
        assertFalse(Double.NaN == Double.NaN);
        assertFalse(0/0.0 == 0/0.0);

    }

    @Test
    void should_get_NaN() {
        assertTrue(Double.isNaN(0 / 0.0));
    }

    @Test
    void should_not_round_number_when_convert_to_integer() {
        final float floatingPointNumber = 2.75f;
        final int integer = (int) floatingPointNumber;

        // TODO: Please change the result to pass the test.
        // <!--start

        final int expected = 2;
        // --end-->

        assertEquals(expected, integer);
    }

    @SuppressWarnings("unused")
    @Test
    void should_round_number() {
        final double floatingPointNumber = 2.75;

        // TODO: Please call some method to round the floating point number.
        // <!--start
        final long rounded = Math.round(floatingPointNumber);
        // --end-->

        assertEquals(3L, rounded);
    }

    @Test
    void should_test_math_round() {
        double doubleNumber = 2.49;
        long actual = Math.round(doubleNumber);

        assertEquals(2, actual);
    }

// 只根据小数点后一位来判断
//floor //向下取整
    //ceil //向上取整
    //round //四舍五入取整

    @SuppressWarnings("unused")
    private boolean isNan(double realNumber) {
        // TODO: please implement the method to pass the test.

        return Double.isNaN(realNumber);
    }

    @SuppressWarnings("unused")
    private boolean isInfinity(double realNumber) {
        // TODO: please implement the method to pass the test.

        return Double.isInfinite(realNumber);
    }

    /*
     * The coach should ask the following questions for the correspond test method:
     *
     * - Can we compare NaN using == directly?
     * - Can we compare XXX_INFINITY using == directly?
     */
}
