package com.cultivation.javaBasicExtended.reverseString;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class StringReverser {
    @SuppressWarnings({"WeakerAccess", "unused"})
    public static String[] reverse(String input) {
        if(input == null){
            throw new IllegalArgumentException();
        }

        input = input.trim();

        if(input.length() == 0){
            return new String[0];
        }

        List<String> splitArray = Arrays.asList(input.split(" "));
        Collections.reverse(splitArray);

        return splitArray.toArray(new String[0]);
    }
}
