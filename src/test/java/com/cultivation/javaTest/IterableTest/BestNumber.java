package com.cultivation.javaTest.IterableTest;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static com.cultivation.javaBasicExtended.myUnitTestFramework.MyAssert.assertEquals;

class BestNumber {

    @Test
    void should_test_best_number() {
        ArrayList<Object> result = new ArrayList<>();

        Double end = Math.pow(2, 40);

        MyIterable myIterable = new MyIterable((double) 1, end);

        Iterator iterator = myIterable.iterator();

        while (iterator.hasNext()) {
            Object nextNumber = iterator.next();
            result.add(nextNumber);
        }
        assertEquals(15, result.size());
    }
}
