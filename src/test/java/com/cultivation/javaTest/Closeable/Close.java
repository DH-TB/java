package com.cultivation.javaTest.Closeable;

import java.io.Closeable;
import java.io.IOException;

public class Close implements Closeable {
    private boolean closed = false;

    public Close() {

    }

    public Boolean getClose(){
        return this.closed;
    }

    @Override
    public void close() throws IOException {
        this.closed = true;
        throw new IllegalArgumentException();
    }
}
