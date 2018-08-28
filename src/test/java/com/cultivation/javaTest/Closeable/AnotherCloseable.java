package com.cultivation.javaTest.Closeable;

import java.io.Closeable;
import java.io.IOException;

public class AnotherCloseable implements Closeable {
    @Override
    public void close() throws IOException {
        throw new IllegalAccessError();
    }
}
