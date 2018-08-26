package com.cultivation.javaBasicExtended.reverseString;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class StringReverser {
    @SuppressWarnings({"WeakerAccess", "unused"})
    public static String[] reverse(String input) {
        if(input == null){
            throw new IllegalArgumentException();
        }

        if(input.length() == 0){
            return new String[0];
        }

        List splitArray = Arrays.asList(input.split(" "));
        Collections.reverse(splitArray);

        return (String[]) splitArray.toArray(new String[0]);
    }
}
