package com.cultivation.javaBasic;

import com.cultivation.javaBasic.showYourIntelligence.DistinctIterable;
import com.cultivation.javaBasic.showYourIntelligence.Sequence;
import com.cultivation.javaBasic.util.RandomCharacterIterable;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

import static junit.framework.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class CollectionsTest {
    @Test
    void should_go_through_an_iterator() {
        ArrayList<String> collection = new ArrayList<>();
        collection.add("Hello");
        collection.add("World");
        collection.add("!");

        Iterator<String> iterator = collection.iterator();

        assertIterableEquals(Arrays.asList("Hello", "World", "!"), createList(iterator));
    }

    @SuppressWarnings({"unused", "UnnecessaryLocalVariable"})
    private static List<String> createList(Iterator<String> iterator) {
        List<String> list = new ArrayList<>();

        // TODO: you could ONLY use `Iterator.hasNext` and `Iterator.next` API to copy items to a `List`. No `for` is allowed.
        // <--start
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        // --end-->

        return list;
    }

    @Test
    void should_create_a_sequence_without_putting_all_items_into_memory() {
        Sequence sequence = new Sequence(4, 10);
        assertIterableEquals(Arrays.asList(4, 5, 6, 7, 8, 9), sequence);
    }

//  会校验两个可迭代对象是否深度一致

    @Test
    void should_predict_linked_list_operation_result() {
        LinkedList<String> staff = new LinkedList<>();

        staff.add("Amy");
        staff.add("Bob");
        staff.add("Carl");

        ListIterator<String> iterator = staff.listIterator();
        Iterator<String> iterator1 = staff.iterator();
        iterator1.remove();

        iterator.next();         //返回当前
        iterator.add("Juliet");
        iterator.previous();    //返回前一个
        iterator.remove();

        assertEquals("Bob", iterator.next());

        // TODO: please modify the following code to pass the test
        // <--start
        final List<String> expected = Arrays.asList("Amy", "Bob", "Carl");
        // --end-->

        assertIterableEquals(expected, staff);
    }

    @Test
    void should_generate_distinct_sequence_on_the_fly() {
        // NOTE: This test may execute for a while. But it is okay if your impl is correct.
        final int oneGagaChars = 1024 * 1024 * 1024;
        RandomCharacterIterable characters = new RandomCharacterIterable(oneGagaChars, new Character[]{'a', 'b'});

        List<Character> distinct = new DistinctIterable<>(characters).toList();
        distinct.sort(Character::compareTo);
        assertIterableEquals(Arrays.asList('a', 'b'), distinct);
    }

    @Test
    void should_reflects_back_to_original_list_for_sub_range() {
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 12; ++i) {
            integers.add(i);
        }

        for(Integer integer : integers){
            integers.set(0, 2);
//            integers.add(1);
        }

        // iterator 可以修改元素的值，不能修改list长度

        List<Integer> subList = integers.subList(3, 10);
        subList.clear();

        // TODO: please modify the following code to pass the test
        // <--start
        final List<Integer> expected = Arrays.asList(2, 1, 2, 10, 11);
        // --end-->

        assertIterableEquals(expected, integers);
    }

    @Test
    void should_test_Integer_immutable() {
        Integer integer = 1;
        Integer another = integer;
        integer++;

        assertNotSame(another, integer);
    }
}

/*
 * - Can you expect the order returned when iterating over a `HashSet<T>`?
 * - What is an `ArrayList`, `LinkedList`, `ArrayDeque`, `HashSet`, `HashSet`, `TreeSet`, `EnumSet`, `LinkedHashSet`,
 *   `PriorityQueue`, `HashMap`, `TreeMap`, `EnumMap`, `LinkedHashMap`
 * - What if an collection is modified while an iterator is still iterating?
 * - Can you add or remove items to the list that is returned by `Array.asList` or `Collections.nCopies`?
 * - What are the differences between HashMap and HashSet?
 * - What is size(), and what capacity?
 */
