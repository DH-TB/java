package com.cultivation.javaBasic.showYourIntelligence;

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
        this.forEach(result::add); //this 实现iterable，重写iterator()
        return result;
    }
}

class DistinctIterator<E> implements Iterator<E> {
    // TODO: Implement the class to pass the test. Note that you cannot put all items into memory or you will fail.
    // <--start
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final Iterator<E> iterator;
    private Set<E> set = new HashSet<>();
    private E cachedNext;

    DistinctIterator(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        while (iterator.hasNext()) {
            E next = iterator.next();

            if (set.add(next)) {
                cachedNext = next;
                return true;
            }
        }
        return false;
    }

    @Override
    public E next() {
        if(cachedNext == null){
            return iterator.hasNext() ? iterator.next() : null;
        }
        return cachedNext;
    }

    // --end->
}