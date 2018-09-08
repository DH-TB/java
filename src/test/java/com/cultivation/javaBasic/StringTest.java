package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class StringTest {
    @SuppressWarnings({"StringEquality", "ConstantConditions"})
    @Test
    void should_be_immutable() {
        String originalString = "The original string";
        String modifiedString = originalString.replace("original", "new");

        // TODO: Please modify the following line to pass the test.
        //
        // It is really easy to pass the test. But you have to tell why.
        // <--start
        final Optional<Boolean> areSame = Optional.of(false);
        // --end-->
        assertEquals("The new string", modifiedString);
        assertEquals(areSame.get(), originalString == modifiedString);
    }

    @Test
    void should_test_equal() {
        String string1 = "str";
        String postfix = "r";
        String string2 = "st" + postfix;

        assertEquals(true, string1 == string2);

    }
    //不可变的
    //https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html


    @SuppressWarnings({"StringEquality", "ConstantConditions"})
    @Test
    void all_modification_method_will_create_new_string() {
        String originalString = "The string with tailing space.     ";
        String modifiedString = originalString.trim();

        // TODO: Please modify the following line to pass the test.
        //
        // It is really easy to pass the test. But you have to tell why.
        // <--start
        final Optional<Boolean> areSame = Optional.of(false);
        // --end-->

        assertEquals("The string with tailing space.", modifiedString);
        assertEquals(areSame.get(), originalString == modifiedString);
    }

    @SuppressWarnings({"StringEquality", "ConstantConditions"})
    @Test
    void will_create_new_string_when_concat() {
        String originalString = "Part one. ";
        String copyOfOriginalString = originalString;
        originalString += "Part two.";

        // TODO: Please modify the following line to pass the test.
        //
        // It is really easy to pass the test. But you have to tell why.
        // <--start
        final Optional<Boolean> areSame = Optional.of(false);
        // --end-->

        assertEquals("Part one. Part two.", originalString);
        assertEquals(areSame.get(), originalString == copyOfOriginalString);
    }

    @SuppressWarnings("unused")
    @Test
    void should_taken_string_apart() {
        final String originalString = "Java is great";

        // TODO: Take part of the original string according to expectation.
        // <--start
        final String partOfString = originalString.substring(5);
        // --end-->

        final String expectedString = "is great";

        assertEquals(expectedString, partOfString);
    }

    @SuppressWarnings("unused")
    @Test
    void should_taken_string_apart_continued() {
        final String originalString = "Java is great.";

        // TODO: Take part of the original string according to expectation.
        // <--start
        final String partOfString = originalString.substring(5, 7);
        // 取头不取尾，左闭右开
        // --end-->

        final String expectedString = "is";

        assertEquals(expectedString, partOfString);
    }

    /*
     * Questions on take string apart.
     *
     * - What if the input arguments is out of range of the string?  //StringIndexOutOfBoundsException
     * - What will happen if the the starting index is greater than the ending index?
     * - What will happen if the input string is of null reference?
     */

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_break_string_into_words() {
        final String sentence = "This is Mike";

        // TODO: Extract words in the sentence.
        // <--Start
        String[] words = sentence.split(" ");
        // --End-->

        assertArrayEquals(new String[]{"This", "is", "Mike"}, words);
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_break_string_into_words_customized() {
        final String sentence = "This/is/Mike";

        // TODO: Extract words in the sentence.
        // <--Start
        String[] words = sentence.split("/");
        // --End-->

        assertArrayEquals(new String[]{"This", "is", "Mike"}, words);
    }

    @SuppressWarnings({"unused", "StringBufferReplaceableByString", "MismatchedQueryAndUpdateOfStringBuilder"})
    @Test
    void should_create_ascii_art() {
        final int width = 5;
        final int height = 3;

        // TODO: Create string using StringBuilder
        // <--Start
        StringBuilder builder = new StringBuilder();

        final String char2 = "-";
        final String char3 = " ";

        builder.append(buildChar(char2, height));
        builder.append(buildChar(char3, height));
        builder.append(buildChar(char2, height));


        // --End-->

        final String expected = "|---|\n" +
                "|   |\n" +
                "|---|\n";

        assertEquals(expected, builder.toString());
    }

    private StringBuilder buildChar(String char2, int height) {
        StringBuilder builder = new StringBuilder();

        final String char1 = "|";

        builder.append(char1);
        for (int i = 0; i < height; i++) {
            builder.append(char2);
        }
        builder.append(char1).append("\n");
        return builder;
    }

    @Test
    void should_create_ascii_art1() {
        final int width = 5;
        final int height = 3;

        final String expected = "|---|\n" +
                "|   |\n" +
                "|---|\n";

        StringBuilder stringBuilder = getChar(height, width);

        assertEquals(expected, stringBuilder.toString());
    }

    private StringBuilder getChar(int height, int width) {
        StringBuilder builder = new StringBuilder();

        final String char1 = "|";

        for (int row = 0; row < height; row++) {
            builder.append(char1);

            getColElement(width, builder, row);

            builder.append(char1);
            builder.append("\n");
        }
        return builder;
    }

    private void getColElement(int width, StringBuilder builder, int row) {
        final String char2 = "-";
        final String char3 = " ";

        for (int j = 0; j < width - 2; j++) {
            if (row == 1) {
                builder.append(char3);
            } else {
                builder.append(char2);
            }
        }
    }

    @SuppressWarnings("unused")
    @Test
    void should_calculate_checksum_of_a_string() {
        final String text = "A quick brown fox jumps over a lazy dog.";

        int sum = 0;
        // TODO: Write some code to calculate the checksum of the string. The checksum is the sum of each string char.
        // <--Start
        // --End-->
        for (int i = 0; i < text.length(); i++) {
            sum += text.charAt(i);
        }

        assertEquals(3655, sum);
    }

    @Test
    void should_transfer_when_calculate() {
        final String text = "a";
        double sum = text.charAt(0);
        System.out.println(sum);
    }

    //http://ascii.911cha.com/

    @Test
    void should_convert_unicode_escape() {
        final String expected = "なにこれ";

        // TODO: Write actual string using unicode escape. The unicode is as follows:
        // な - U+306a
        // に - U+306b
        // こ - U+3053
        // れ - U+308c
        // <--Start
        final String actual = "\u306a\u306b\u3053\u308c";
        // --End-->

        assertEquals(expected, actual);
    }

    //http://www.ruanyifeng.com/blog/2007/10/ascii_unicode_and_utf-8.html

    @SuppressWarnings("unused")
    @Test
    void should_reverse_a_string() {
        final String original = "123456";

        // TODO: Modify the following code to create new string from original String
        // <--Start
        final String reversed = new StringBuilder(original).reverse().toString();
        // --End-->

        assertEquals("654321", reversed);
    }

    @Test
    void should_test_final_char_array_can_assign_value() {
        final char[] array = new char[]{'1', '2', '3', '4', '5', '6'};

        for (int i = 0; i < array.length / 2; i++) {
            char temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }

        final char[] expectArray = new char[]{'6', '5', '4', '3', '2', '1'};
        assertArrayEquals(array, expectArray);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_compare_string_with_different_cases() {
        final String upperCased = "HELLO";
        final String lowerCased = "hello";

        Optional<Boolean> equalResult = Optional.of(upperCased.equals(lowerCased));
        Optional<Boolean> equalIgnoreCaseResult = Optional.of(upperCased.equalsIgnoreCase(lowerCased));

        // TODO: Please change the value of the following 2 lines to pass the test.
        // <--start
        Optional<Boolean> actualResultOfEqual = Optional.of(false);
        Optional<Boolean> actualResultOfEqualIgnoreCase = Optional.of(true);
        // --end-->

        assertEquals(equalResult, actualResultOfEqual);
        assertEquals(equalIgnoreCaseResult, actualResultOfEqualIgnoreCase);
    }


    @Test
    void should_get_code_point_count() {
        final String withSurrogatePairs =
                new String(Character.toChars(0x20B9F)) + " is a character that you may not know";

        // 0x20B9F 20位，
        // 一个char，2个字节，16位，放不下，需要占用两个char,但实际是一个codePoint

        // TODO: please modify the following code to pass the test
        // <--start
        // TODO: please write down the result directly.
        final int expectedCharLength = 39;
        // TODO: please call some method to calculate the result.
        final int actualCodePointLength = Character.codePointCount(withSurrogatePairs, 0, withSurrogatePairs.length());

        // --end-->
        assertEquals(expectedCharLength, withSurrogatePairs.length());
        assertEquals(38, actualCodePointLength);
    }

    //https://docs.oracle.com/javase/7/docs/api/java/lang/Character.html#toChars(int)

    @Test
    void should_copy_all_code_point_to_array() {
        final String withSurrogatePairs =
                new String(Character.toChars(0x20B9F)) + " is funny";

        final int[] codePoints = getCodePoints(withSurrogatePairs);

        assertArrayEquals(
                new int[]{0x20B9F, (int) ' ', (int) 'i', (int) 's', (int) ' ', (int) 'f', (int) 'u', (int) 'n', (int) 'n', (int) 'y'},
                codePoints);
    }
    // 代理

    @Test
    void should_format_string() {
        final String name = "Harry";
        final int age = 23;

        String text = String.format("Hello, %s. Next year, you will be %d.", name, age);

        // TODO: please modify the following code to pass the test
        // <--start
        final String expectedText = "Hello, Harry. Next year, you will be 23.";
        // --end-->
        assertEquals(expectedText, text);
    }

    private int[] getCodePointsFromString(String withSurrogatePairs) {

        int codePointLength = Character.codePointCount(withSurrogatePairs, 0, withSurrogatePairs.length());
        int[] codePointArray = new int[codePointLength];
        int codePointIndex = 0;

        for (int charIndex = 0; charIndex < withSurrogatePairs.length(); ) {
            int character = withSurrogatePairs.codePointAt(charIndex);
            codePointArray[codePointIndex++] = character;

            charIndex += Character.charCount(character);
        }
        return codePointArray;
    }

    private int[] getCodePoints(String withSurrogatePairs){
        ArrayList<Object> codePointArray = new ArrayList<>();

        for(int charIndex = 0; charIndex < withSurrogatePairs.length(); ){
            int character = withSurrogatePairs.codePointAt(charIndex);
            codePointArray.add(character);
            charIndex += Character.charCount(character);
        }

        return codePointArray.stream().mapToInt(i -> (int)i).toArray();
    }

    @Test
    void name() {
        boolean[] list = new boolean[10];
        System.out.println(list[0]);
        // all ten elements are assigned to false

        Boolean[] list1 = new Boolean[10];
        // all ten elements are assigned to null (default Object/Boolean value)
        System.out.println(list[0]);

        char[] chars = new char[10];
        System.out.println(chars[0]);
        // '\u0000'

        String[] strings = new String[1];
        System.out.println(strings[0]);
        // null

        int[] ints = new int[2];
        System.out.println(ints[0]);
        // 0


    }
//    https://stackoverflow.com/questions/5389200/what-is-a-java-strings-default-initial-value

    /*
     * - List other string format conversion chars.
     *   * d - decimal integer
     *   * x - hexadecimal integer
     *   * o - octal integer
     *   * f - fixed-point floating point
     *   * e - exponential floating point
     *   * g - general floating point (the shorter of e and f)
     *   * a - hexadecimal floating point
     *   * s - string
     *   * c - character
     *   * b - boolean
     *   * h - hash code
     *   * n - platform dependent line separator
     */
}
