package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.jupiter.api.Assertions.*;

//
class IntegerTypeTest {

    @Test
    void should_get_range_of_primitive_int_type() {
        final int maximum = 0x7fffffff;
        final int minimum = 0x80000000;

        // TODO: You should not write concrete number here. Please find a property or constant instead.
        // <!--start
        final int maximumSymbol = Integer.MAX_VALUE;
        final int minimumSymbol = Integer.MIN_VALUE;
        // --end-->

        assertEquals(maximumSymbol, maximum);
        assertEquals(minimumSymbol, minimum);
    }

    @Test
    void should_be_negative() {
        final int maximum = 0x7fffffff;
        final int minimum = 0x80000000;
        int one = 0x0000001;
        int negativeOne = 0x80000001;

        for (int i = one; i < maximum; i++) {
            assertTrue(i > 0);
        }

        for (int i = negativeOne; i > minimum; i--) {
            assertTrue(i < 0);
        }
    }

    @Test
    void should_test_negative() {
        for (int i = Integer.MIN_VALUE; i < 0; i++) {
            assertEquals(1, i >>> 31);
        }

        int one = 1;
        for (int i = 0; i < 31; i++) {
            assertTrue((one << 1) > 0);
        }
    }

    //https://blog.csdn.net/claram/article/details/76682125

    //应该得到原始 int 类型的范围
    // 0x表示16进制 转换成二进制： 1000,0000,0000,0000,0000,0000,0000,0000 负数 取反后
    // -0111,1111,1111,1111,1111,1111,1111,1111，十进制是  -2^31 = -2147483648
    // 0111 1111 1111 1111 1111 1111 1111 1111   十进制是  2^31-1 = 2147483647
    //一个 Integer 类型占 4 字节，一个字节占 8 位二进制码，因此一个 Integer 总共占 32 位二进制码。去除第一位的符号位，剩下 31 位来表示数值。

    @Test
    void should_get_range_of_primitive_short_type() {
        final short maximum = 32767;
        final short minimum = (short) 0x8000;

        //2^15-1
        //-2^15
        // TODO: You should not write concrete number here. Please find a property or constant instead.
        // <!--start
        final short maximumSymbol = Short.MAX_VALUE;
        final short minimumSymbol = Short.MIN_VALUE;
        // --end-->

        assertEquals(maximumSymbol, maximum);
        assertEquals(minimumSymbol, minimum);
        assertTrue(0x80001000 < 0);
    }


    @Test
    void should_get_range_of_primitive_long_type() {
        final long maximum = 0x7fffffffffffffffL;
        final long minimum = -0x8000000000000000L;

        // TODO: You should not write concrete number here. Please find a property or constant instead.
        // <!--start
        final long maximumSymbol = Long.MAX_VALUE;
        final long minimumSymbol = Long.MIN_VALUE;
        // --end-->

        assertEquals(maximumSymbol, maximum);
        assertEquals(minimumSymbol, minimum);
    }

    @Test
    void should_get_range_of_primitive_byte_type() {
        final byte maximum = 127;
        final byte minimum = -128;

        // TODO: You should not write concrete number here. Please find a property or constant instead.
        // <!--start
        final byte maximumSymbol = Byte.MAX_VALUE;
        final byte minimumSymbol = Byte.MIN_VALUE;
        // --end-->

        assertEquals(maximumSymbol, maximum);
        assertEquals(minimumSymbol, minimum);
    }

    // https://blog.csdn.net/nyistzp/article/details/12029917


    @Test
    void should_overflow_silently() {
        int theNumberWillOverflow = Integer.MAX_VALUE;
        ++theNumberWillOverflow;

        // TODO: Please correct the value to pass the test.
        // <--start
        final int expectedResult = Integer.MIN_VALUE;
        // --end-->

        assertEquals(expectedResult, theNumberWillOverflow);
    }

    // 默默地溢出


    @Test
    void should_underflow_silently() {
        int theNumberWillUnderflow = Integer.MIN_VALUE;
        --theNumberWillUnderflow;

        // TODO: Please correct the value to pass the test.
        // <--start
        final int expectedResult = Integer.MAX_VALUE;
        // --end-->

        assertEquals(expectedResult, theNumberWillUnderflow);
    }
    // Integer.MIN_VALUE 加 -1 -1的补码全都是1（ 1111_1111）
    // 100000 加
    // 111111
    //

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    void should_throw_exception_when_overflow() {
        int theNumberWillOverflow = Integer.MAX_VALUE;

        assertThrows(ArithmeticException.class, () -> add(theNumberWillOverflow, 1));
    }

    @Test
    void just_prevent_lazy_implementation() {
        assertEquals(3, add(1, 2));
    }

    @SuppressWarnings("PointlessArithmeticExpression")
    @Test
    void should_take_care_of_number_type_when_doing_calculation() {
        final double result1 = 2 / 3 * 5;
        final double result2 = 2 * 5 / 3;

        //3.3333333

        // TODO: please modify the following lines to pass the test
        // <!--start
        final double expectedResult1 = 0.0;
        final double expectedResult2 = 3.0;
        // --end-->

//        assertEquals(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        assertEquals(expectedResult1, result1, +1.0E-05);
        assertEquals(expectedResult2, result2, +1.0E-05);
    }

    // http://junit.sourceforge.net/javadoc/org/junit/Assert.html#assertEquals(double,%20double,%20double)
    //误差范围 绝对值小于 0.00001 都可以


    @Test
    void should_truncate_number_when_casting() {
        final int integer = 0x0123_4567;
        final short smallerInteger = (short) integer;

        // TODO: please modify the following lines to pass the test
        // <!--start
        final short expected = 0x4567;
        // --end-->

        assertEquals(expected, smallerInteger);
    }

    //    截取 不保留符号位
    //    基本原则就是整数按字节进行截断，以32位操作系统为例，int是32位的，short是int的一半是int的低16位，char是int的最低8位
    //    强制类型转换的原理: 当将高位变量转换为低位是,只会取高位的后几位直接作为低位的值.
    //    0x0123_4567 = 0b(0000 0001 0010 0011 _ 0100 0101 0110 0111 ) 2^0+2^1+2^2+2^5+2^6+2^8+2^10+2^14 = 256+1024+16384 =17767
    //    https://docs.oracle.com/javase/8/docs/technotes/guides/language/underscores-literals.html

    @Test
    void should_truncate() {
        final int negativeInteger = 0x7fff_ffff;
        final short negativeSmallInteger = (short) negativeInteger;

        assertTrue(negativeInteger > 0);
        assertTrue(negativeSmallInteger < 0);
    }

    @Test
    void should_increment() {
        int integer = 3;

        int result = integer++;

        // TODO: please modify the following code to pass the test
        // <--start
        final int expectedCurrentInteger = 4;
        final int expectedResult = 3;
        // --end-->

        assertEquals(expectedCurrentInteger, integer);
        assertEquals(expectedResult, result);
    }

    @Test
    void should_increment_2() {
        int integer = 3;

        int result = ++integer;

        // TODO: please modify the following code to pass the test
        // <--start
        final int expectedCurrentInteger = 4;
        final int expectedResult = 4;
        // --end-->

        assertEquals(expectedCurrentInteger, integer);
        assertEquals(expectedResult, result);
    }

    private int add(int left, int right) {
        // TODO: Please implement the method. Adding two numbers.
        // The method should throw ArithmeticException if overflow or underflow happens.
        return Math.addExact(left, right);
    }

    //https://stackoverflow.com/questions/38812863/how-to-cause-an-exception-on-integer-overflow/38812905

    /*
     * The coach should ask the following questions to make the candidates be focused on the number representations:
     *
     * - How many bytes needed to store a(n) int/long/short/byte.
     * - How many bits are there in a(n) int/long/short/byte.
     * - Why integer number over/underflow is dangerous?
     * - What is `final` variable?
     * - Can Java use uninitialized variable?
     * - Among all the integer types. Which one can be implicitly convert to another.
     * - What is the resulting type for the operation (+ - * / %) of two `short` variable?
     * - When two values are combined with a binary operator both operands are converted to a common type before
     *   the operation is carried out. Do you know the conversion rules?
     *   * If either of the operands is of type double, the other one will be converted to a double.
     *   * Otherwise, if either of the operands is of type float, the other one will be converted to a float.
     *   * Otherwise, if either of the operands is of type long, the other one will be converted to a long.
     *   * Otherwise, both operands will be converted to an int.
     */
}
