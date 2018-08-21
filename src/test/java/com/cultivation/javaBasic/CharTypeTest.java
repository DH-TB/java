package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.EscapedChars;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CharTypeTest {
    @Test
    void should_describe_escaped_chars() {
        // TODO: please modify the following code to pass the test
        // <--start
        final char backspace = '\b'; //退格
        final char tab = '\t';
        final char lineFeed = '\n';//换行
        final char carriageReturn = '\r'; //回车
        final char doubleQuote = '\"'; //双引号
        final char singleQuote = '\''; //单引号
        final char backslash = '\\'; //反斜杠
        // --end-->

        assertEquals(EscapedChars.BACKSPACE.getValue(), backspace);
        assertEquals(EscapedChars.TAB.getValue(), tab);
        assertEquals(EscapedChars.LINE_FEED.getValue(), lineFeed);
        assertEquals(EscapedChars.CARRIAGE_RETURN.getValue(), carriageReturn);
        assertEquals(EscapedChars.DOUBLE_QUOTE.getValue(), doubleQuote);
        assertEquals(EscapedChars.SINGLE_QUOTE.getValue(), singleQuote);
        assertEquals(EscapedChars.BACKSLASH.getValue(), backslash);
    }
//    UTF-8 1 / 2 / 3
//    UTF-16 2 / 4
//    UTF-32 4

    /*
     * - Could a char represent one unicode character? Or, in other words, could a char represent a code point?
     * - How many bits are needed to represents one code point in UTF-16? What about UTF-8 and UTF-32?
     * - In Java, which encoding is used by char type? The UTF-16 encoding or UTF-8 encoding.
     * - Why there are many methods in Character class accepting an int parameter rather than char?
     */
}

// String的每一个char其实都可以用一个Unicode编码表示，
//一个完整的Unicode字符叫CodePoint
//        一个Java char 叫代码单元code unit;
// http://www.voidcn.com/article/p-vnpftcts-k.html