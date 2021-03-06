package com.cultivation.javaTest.IterableTest;

import java.util.Iterator;

class MyIterable<T extends Number & Comparable<T>> implements Iterable<T> {
    private T start;
    private T end;

    MyIterable(T start, T end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterableImpl<>(start, end);
    }
}


class MyIterableImpl<E extends Number & Comparable<E>> implements Iterator<E> {
    private E start;
    private E end;

    MyIterableImpl(E start, E end) {
        this.start = start;

        this.end = end;
    }

    @Override
    public boolean hasNext() {
        double pow =  Math.pow((Double) start, 10);
        double end = (Double)this.end;
        return pow < end;
    }

    @Override
    public E next() {
        Double start = (Double) this.start;
        start++;
        return (E) start;
    }
}
