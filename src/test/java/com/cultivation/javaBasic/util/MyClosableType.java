package com.cultivation.javaBasic.util;

import java.io.Closeable;

public class MyClosableType implements Closeable {
    private ClosableStateReference reference;

    public MyClosableType(ClosableStateReference reference) {

        this.reference = reference;
    }

    public boolean isClosed() {
        return reference.isClosed();
    }

    @Override
    public void close() {
        reference.close();
    }
}
