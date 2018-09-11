package com.cultivation.javaTest.IterableTest;

import java.util.Iterator;

class MyIterable<T> implements Iterable<T> {
    private T start;
    private T end;

    MyIterable(T start, T end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Iterator<T> iterator() {
//        return new MyIterableImpl<>(start, end);
        return null;
    }
}

/*
class MyIterableImpl<E> implements Iterator<E> {
    private E start;
    private E end;

    MyIterableImpl(E start, E end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean hasNext() {
        double pow =  Math.pow((Double) start, 10);
        boolean b = pow < end;
        return b;
    }

    @Override
    public E next() {
        E element = start++;
        return element;
    }


}*/
