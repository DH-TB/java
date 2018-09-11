package com.cultivation.javaBasicExtended.printMatchedPattern;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MyGrep {
    @SuppressWarnings({"WeakerAccess", "unused", "RedundantThrows"})
    public static String[] grep(Reader reader, String pattern) throws IOException {
        // TODO: please implement the method to pass all the tests.
        // <--start
        if(reader == null || pattern == null || pattern.equals("")){
            throw new IllegalArgumentException();
        }
        Pattern pattern1 = Pattern.compile(pattern);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        while ((line = bufferedReader.readLine()) != null){
            Matcher matcher = pattern1.matcher(line);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                stringBuilder.append((line), start, end).append(",");
            }
        }
        return stringBuilder.toString().split(",");
        // --end-->
    }
}
