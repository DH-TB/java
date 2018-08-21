package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BooleanOperatorsTest {

    //应该执行逻辑布尔运算符

    @SuppressWarnings({"PointlessBooleanExpression", "ConstantConditions"})
    @Test
    void should_perform_logical_boolean_operations() {
        boolean[] actualResults = {
                true && true,
                true && false,
                false && false,
                true || true,
                true || false,
                false || false,
                true & true,
                true & false,
                false & false,
                true | true,
                true | false,
                false | false,
                3 == 7,
                4 != 5
        };

        // TODO: please modify the following code to pass the test
        // <--start
        boolean[] expectedResult = {true, false, false, true, true, false, true, false, false, true, true, false, false, true};
        // --end-->

        assertArrayEquals(expectedResult, actualResults);
    }
    //&&（短路与），&（与），|（或），||（短路或）。
    //&&和&都是表示与，区别是&&只要满足第一个条件，后面条件就不再判断。而&要对所有的条件都进行判断。
    //https://www.jianshu.com/p/8cf5af30f245

    @Test
    void should_do_bitwise_and_boolean_operation() {
        final int value = 0x1234_abcd;
        final int mask = 0x000f_ff00;

        // TODO: please write down the result directly to pass the test.
        // <--start
        final int expected = 0x0004_ab00;
        // --end-->

        // 0001 0010 0011 0100 1010 1011 1100 1101
        // 0000 0000 0000 1111 1111 1111 0000 0000
        // 0000 0000 0000 0100 1010 1011 0000 0000

        assertEquals(expected, value & mask);
    }

    @Test
    void should_do_bitwise_or_boolean_operation() {
        final int value = 0x1234_0000;
        final int mask = 0x0000_abcd;

        // TODO: please write down the result directly to pass the test.
        // <--start
        final int expected = 0x1234_abcd;
        // --end-->

        assertEquals(expected, value | mask);
    }

    @Test
    void should_do_bitwise_not_operation() {
        final int value = 0x0000_ffff;

        // TODO: please write down the result directly to pass the test.
        // <--start
        final int expected = 0xffff_0000;
        // --end-->

        assertEquals(expected, ~value);
    }
}
