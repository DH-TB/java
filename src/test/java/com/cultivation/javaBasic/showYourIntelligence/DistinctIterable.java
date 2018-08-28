package com.cultivation.javaBasic.showYourIntelligence;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class DistinctIterable<T> implements Iterable<T> {
    private Iterable<T> iterable;

    public DistinctIterable(Iterable<T> iterable) {
        this.iterable = iterable;
    }

    @Override
    public Iterator<T> iterator() {
        return new DistinctIterator<>(iterable.iterator());
    }

    public List<T> toList() {
        ArrayList<T> result = new ArrayList<>();
        this.forEach(result::add);
        return result;
    }
}

class DistinctIterator<E> implements Iterator<E> {
    // TODO: Implement the class to pass the test. Note that you cannot put all items into memory or you will fail.
    // <--start
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final Iterator<E> iterator;
    private List<E> list = new ArrayList<>();
    private E cachedNext;
    DistinctIterator(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        while (iterator.hasNext()) {
            E next = iterator.next();

            if (!list.contains(next)) {
                list.add(next);
                cachedNext = next;
                return true;
            }
        }
        return false;
    }

    @Override
    public E next() {
        return cachedNext;
    }
    // --end->
}