package com.cultivation.javaTest.Closeable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CloseableTest {
    @Test
    void should_throw_exception() {
        final Close[] copyClose = {null};
        assertThrows(IllegalAccessError.class, () -> {
            try (Close close = new Close(); AnotherCloseable anotherCloseable = new AnotherCloseable())
            {
                copyClose[0] = close;
            }
            assertTrue(copyClose[0].getClose());

        });

    }
}