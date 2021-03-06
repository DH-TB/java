package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.AnimeCharacter;
import com.cultivation.javaBasic.util.KeyValuePair;
import com.cultivation.javaBasic.util.ValueHolder;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.*;

import static org.junit.jupiter.api.Assertions.*;

//
class StreamingTest {
    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_turn_collection_into_stream() {
        List<String> words = Arrays.asList("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> wordStream = words.stream();
        // --end-->
        {
            assertIterableEquals(
                    words,
                    wordStream.collect(Collectors.toList())
            );
        }
    }

//    list.stream()    collection
//    Arrays.stream()  array
//    Stream.of()      可变参数

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_turn_array_into_stream() {
        String[] words = {"a", "quick", "brown", "fox", "jumps", "over"};
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> wordStream = Arrays.stream(words);
        // --end-->
        {
            assertArrayEquals(
                    words,
                    wordStream.toArray(String[]::new)
            );
        }
    }

    @Test
    void should_other_primitive_type_should_be_boxing() {
        boolean[] booleans = {true, false};
        Stream<boolean[]> stream3 = Arrays.stream(new boolean[][]{booleans});

        long[] longs = new long[]{1};
        long[] longs1 = {(long) 1};
        LongStream stream1 = Arrays.stream(longs);
        LongStream stream2 = Arrays.stream(longs1);

        int[] ints = {1};
        IntStream stream = Arrays.stream(ints);


        float[] floats = {1f, 2f};
        Stream<float[]> stream4 = Arrays.stream(new float[][]{floats});
        Stream.of(floats);

        short[] short1 = {(short) 1};
        Stream<short[]> stream5 = Arrays.stream(new short[][]{short1});
    }

    //只有int long double,其他基本类型可以显示转换为包装类，或者使用stream.of(T... a)

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_empty_stream() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> emptyWordStream = Stream.empty();

        // --end-->

//      assertEquals(0, emptyWordStream.count());
        assertEquals(0, emptyWordStream.mapToLong(e -> 1L).sum());
    }

    @Test
    void should_test_is_origal_stream() {
        IsClose close = new IsClose();
        Stream<Integer> integerStream = Stream.of(1);
        integerStream.onClose(() -> {
            close.isClose = false;
        });


        Boolean[] booleans = {true};
        int[] ints = {0};
        ArrayList<Integer> arrayList = new ArrayList<>();

        integerStream.onClose(new Runnable() {
            @Override
            public void run() {
                booleans[0] = false;
                ints[0] = 1;
                arrayList.add(0);
            }
        });
        Stream<Integer> filterdStream = integerStream.filter(integer -> integer == 1);

        assertTrue(close.isClose);
        assertEquals(0, ints[0]);
        assertEquals(0, arrayList.size());

        assertNotSame(integerStream, filterdStream);
    }

    class IsClose {
        boolean isClose = true;
    }

    //    toArray() 没有关闭流(close)
    //    原来的流link
    //    private static <T> Stream<T> empty()

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_with_same_items() {
        // TODO: please modify the following code to pass the test
        // <--start

//        Stream<String> infiniteEchos = Stream.iterate("Echo", i -> i + "").limit(10001);
        Stream<String> infiniteEchos = Stream.generate(() -> "Echo").limit(10001);

        // --end-->
        {
            assertEquals("Echo", infiniteEchos.skip(10000).findFirst().get());
        }
    }


    class Count {
        int time;
    }

    @Test
    void should_not_execute_when_middle_operation() {
        Count count = new Count();

//        IntStream.iterate(0, (num) -> {
//            count.time++;
//            return num++;
//        }).skip(10000).findFirst();   // 10001
//
//        Stream.generate(() -> {
//            count.time++;
//            return 1;
//        }).skip(10000).findFirst();  // 10001

//        IntStream.generate(() -> {
//            count.time++;
//            return 1;
//        }).skip(10000).findFirst();  // 10001

//        Stream.iterate(0, (num) -> {
//            count.time++;
//            return num++;
//        }).skip(10000).findFirst();   // 10000


        Stream.iterate(0, (num) -> {
            count.time++;
            return num++;
        }).limit(10001).skip(10000).findFirst();   // 10000

        //limit次数大1
        assertEquals(10000, count.time);
    }


    //filter skip limit map 不会执行,遇到 get()/ toArray() 等获取值的时候，才会执行

    @Test
    void should_generate_string_array() {
        Optional<String> first = Stream.generate(() -> "Echo").findFirst();
        assertEquals(first.get(), "Echo");

//        String[] array = Stream.generate(() -> "Echo").toArray(String[]::new);
//        assertEquals(array.getClass().getComponentType(), String.class);
    }

    @Test
    void should_test_generate_times() {
        Time times = new Time();
        Stream<String> infiniteEcho = Stream.iterate("Echo", (i) -> {
            times.time++;
            return i + "";   //10000
        });

        Stream<String> infiniteEchos = Stream.generate(() -> {
            times.time++;
            return "Echo";  //10001
        });
        Stream<String> skip = infiniteEchos.skip(10000);
        assertEquals(0, times.time);

        skip.findFirst();
        assertEquals(10001, times.time);

    }

    class Time {
        int time = 0;
    }

//    generate(Supplier<T> s)
//    Returns an infinite sequential unordered stream where each element is generated by the provided Supplier.
//    iterate(起始值，FUnction<T>) 第一次也不会执行，直接返回起始值，

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_of_sequence() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Integer> infiniteSequence = Stream.iterate(0, i -> i + 1).limit(10001);
        // --end-->
        assertEquals(10000, infiniteSequence.skip(10000).findFirst().get().intValue());

    }

    @SuppressWarnings({"TryFinallyCanBeTryWithResources", "unused", "ConstantConditions"})
    @Test
    void should_be_able_to_filter_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        Stream<String> filtered = wordStream.filter(word -> word.length() > 4);   // Predicate 返回boolean
        // --end-->
        {
            assertArrayEquals(new String[]{"quick", "brown", "jumps"}, filtered.toArray(String[]::new));
        }
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_map_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start

//      Reference to an instance method of an arbitrary object of a particular type

        Stream<String> filtered = wordStream.map(s -> s.toUpperCase());
        // --end-->
        {
            assertArrayEquals(
                    new String[]{"A", "QUICK", "BROWN", "FOX", "JUMPS", "OVER"},
                    filtered.toArray(String[]::new));
        }
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_map_item_to_a_new_type() {
        Stream<String> nameStream = Stream.of("Naruto", "Kisuke", "Tomoya");

        // TODO: please modify the following code to pass the test
        // <--start

        Stream<AnimeCharacter> characters = nameStream.map(AnimeCharacter::new);

        // --end-->
        {
            AnimeCharacter[] actual = characters.toArray(AnimeCharacter[]::new);
            assertArrayEquals(
                    new AnimeCharacter[]{
                            new AnimeCharacter("Naruto"),
                            new AnimeCharacter("Kisuke"),
                            new AnimeCharacter("Tomoya")
                    },
                    actual);
        }
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_flatten_stream_of_streams() {
        Stream<Stream<Character>> streamStream = Stream.of("Hello", "World").map(string -> string.chars().mapToObj(item -> (char) item));

        Stream<Character> characterStream = streamStream.flatMap(item -> item);

        Character[] characters = characterStream.toArray(Character[]::new);

        Character[] expected = {'H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd'};

        assertArrayEquals(expected, characters);
    }


//    https://stackoverflow.com/questions/31992290/how-to-flatmap-a-stream-of-streams-in-java
//    https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#flatMap-java.util.function.Function-

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_create_sequence_of_specified_size() {
        Stream<Integer> infiniteSequence = Stream.iterate(0, i -> i + 1);

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Integer> finiteStream = infiniteSequence.limit(10);
        // --end-->
        {
            assertArrayEquals(
                    new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                    finiteStream.toArray(Integer[]::new)
            );
        }
    }

    @Test
    void should_test_limit_execute_time() {
        Count count = new Count();
        int limitTime = 10;

        Integer[] integers = Stream.iterate(0, integer -> {
            count.time++;
            return integer + 1;
        }).limit(limitTime).toArray(Integer[]::new);

        assertEquals(limitTime - 1, count.time);
    }


    @Test
    void should_test_concat_sorted() {
        Stream<Character> worldStream = Stream.of('c', 'b');
        Stream<Character> worldStream1 = Stream.of('a', 'd');

        Object[] actual = Stream.concat(worldStream.sorted(), worldStream1.sorted()).toArray();

        Object[] expectConcatNotSort = Stream.of('b', 'c', 'a', 'd').toArray();
        assertArrayEquals(expectConcatNotSort, actual);

        Object[] expectConcatSort = Stream.of('a', 'b', 'c', 'd').toArray();
        assertNotSame(expectConcatSort, actual);
    }


    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_concat_streams() {
        Stream<Character> helloStream = Stream.of('H', 'e', 'l', 'l', 'o');
        Stream<Character> worldStream = Stream.of('W', 'o', 'r', 'l', 'd');

        // TODO: please modify the following code to pass the test
        // <--start
//        Stream<Character> concatStream = Stream.concat(helloStream, worldStream);
//        // --end-->
//        {
//            assertArrayEquals(
//                    letters("HelloWorld").toArray(Character[]::new),
//                    concatStream.toArray(Character[]::new)
//            );
//        }
        Stream<Character> concat = Stream.concat(helloStream, worldStream);

        String[] expected = {"HelloWorld"};
        Character[] characters = Arrays.stream(expected).flatMap(string -> string.chars().mapToObj(i -> (char) i)).toArray(Character[]::new);

        assertArrayEquals(characters, concat.toArray(Character[]::new));
    }

    @SuppressWarnings({"SpellCheckingInspection", "unused", "ConstantConditions"})
    @Test
    void should_get_unique_items() {
        Stream<Character> characterStream = letters("aquickbrownfox");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Character> distinct = characterStream.distinct();
        // --end-->
        {
            Character[] characters = distinct.sorted().toArray(Character[]::new);

            assertArrayEquals(
                    new Character[]{'a', 'b', 'c', 'f', 'i', 'k', 'n', 'o', 'q', 'r', 'u', 'w', 'x'},
                    characters
            );
        }
    }

    @Test
    void should_test() {
        Stream<Character> characterStream = letters("ofoa");
        Stream<Character> distinct = characterStream.distinct();

        Character[] character = distinct.unordered().toArray(Character[]::new);

        Character[] characters = {'o', 'f', 'a'};

        assertArrayEquals(characters, character);
    }

    ////////////// ?
    @Test
    void should_hook_stream_generation() {
        ValueHolder<Integer> holder = new ValueHolder<>();
        holder.setValue(0);

        Stream<Integer> hookStream = Stream
                .iterate(0, i -> i + 1)
                .limit(20)
                .filter(v -> v % 2 == 0)
                .peek(v -> holder.setValue(holder.getValue() + v));

        hookStream.forEach(i -> {
        });   //终端操作，为了执行

        // TODO: please modify the following code to pass the test
        // <--start
        final int expected = 90;
        //从0加到18
        // --end-->
        assertEquals(expected, holder.getValue().intValue());
    }

    @Test
    void should_test_peek() {
        Stream.of("one", "two", "three")
                .filter(item -> item.length() > 2)
                .peek(item -> System.out.println("filter:" + item))
                .map(item -> item.toUpperCase())
                .peek(item -> System.out.println("map:" + item))
                .collect(Collectors.toList());
    }

    @Test
    void should_test_peek_() {
        ValueHolder<Integer> holder = new ValueHolder<>();
        holder.setValue(1);

        int num = 2;

        Stream.of(holder).peek(v -> holder.setValue(num)).forEach(integerValueHolder -> {});

        assertEquals(num, holder.getValue().intValue());
    }

    // peek里面可以修改值，会对原本的stream 有影响
    
    @SuppressWarnings({"ConstantConditions", "unchecked", "OptionalAssignedToNull"})
    @Test
    void should_throws_if_get_value_of_empty_optional() {
        // TODO: please create an empty optional and specify the concrete exception type.
        // <--start
        Optional<String> empty = Optional.empty();
        Class errorType = NoSuchElementException.class;
        // --end-->

        assertThrows(errorType, empty::get);
    }

    @Test
    void should_provide_a_default_value_using_or_else() {
        Optional<String> empty = Optional.empty();
        Optional<String> nonEmpty = Optional.of("great");

        String emptyResult = getValue(empty, "default value");
        String nonEmptyResult = getValue(nonEmpty, "default value");

        assertEquals("default value", emptyResult);
        assertEquals("great", nonEmptyResult);
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_throw_for_empty_optional() {
        Optional<String> empty = Optional.empty();

        // TODO: In the `Runnable` object. Please throw IllegalStateException when `empty` is not present.
        // <--start
        Runnable emptyRunnable = () -> {
            if (!empty.isPresent()) {
                throw new IllegalStateException();
            }
        };
        // --end-->

        assertThrows(IllegalStateException.class, emptyRunnable::run);
    }

    //????
    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "ConstantConditions"})
    @Test
    void should_process_value_if_present() {
        Optional<String> optional = Optional.of("word");
        List<String> result = new ArrayList<>();

        // TODO: please add the upper-cased value to result if `optional` is present in `Consumer<Optional<String>>`
        // TODO: implementation.
        // <--start
        Consumer<Optional<String>> optionalConsumer = (option) -> {
            if (option.isPresent()) {
                result.add(option.get().toUpperCase());
            }
        };

        // --end-->

        optionalConsumer.accept(optional);

        assertEquals("WORD", result.get(0));
    }

    @SuppressWarnings({"ConstantConditions", "MismatchedQueryAndUpdateOfCollection"})
    @Test
    void should_map_value_if_present() {
        Optional<String> optional = Optional.of("word");
        Optional<String> empty = Optional.empty();

        List<String> result = new ArrayList<>();

        // TODO: The `Function<Optional<String>, Optional<Boolean>>` will map `Optional<String>` to `Optional<Boolean>`
        // TODO: please add the upper-cased value to `result` list if optional is present. Then return the boolean
        // TODO: mapping result of `result.add`.
        // <--start
        Function<Optional<String>, Optional<Boolean>> mapping = (option) -> option.map(s -> result.add(option.get().toUpperCase()));
        // --end-->

        Optional<Boolean> mappingResult = mapping.apply(optional);
        Optional<Boolean> emptyMappingResult = mapping.apply(empty);

        assertTrue(mappingResult.orElseThrow(IllegalStateException::new));
        assertEquals("WORD", result.get(0));
        assertFalse(emptyMappingResult.isPresent());
    }
    //isPresent() 如果值存在，返回 true，否则返回 false

////////？

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_flat_map_optional_value_do_chain_method() {
        Stream<YieldOptional> emptyStream = Stream.of(1, 2, 3)
                .filter(i -> i > 4)
                .map(i -> new YieldOptional());
        Stream<YieldOptional> nonEmptyStream = Stream.of(1, 2, 3)
                .filter(i -> i > 1)
                .map(i -> new YieldOptional());

        // TODO: The `findFirstAndGet` interface will find first item in stream. If it can be found, map it with
        // TODO: `YieldOptional.get` method. Otherwise, returns empty Optional.
        // <--start

        Function<Stream<YieldOptional>, Optional<String>> findFirstAndGet = (option) -> option.findFirst().flatMap(YieldOptional::get);

        // --end-->

        Optional<String> emptyStreamResult = findFirstAndGet.apply(emptyStream);
        Optional<String> nonEmptyStreamResult = findFirstAndGet.apply(nonEmptyStream);

        assertFalse(emptyStreamResult.isPresent());
        assertTrue(nonEmptyStreamResult.isPresent());
        assertEquals("Hello", nonEmptyStreamResult.get());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_result() {
        Stream<String> stream = Stream.of("Hello", "What", "is", "your", "name").parallel();

        // TODO: please implement toList collector using `stream.collect`. You cannot use existing `toList` collector.
        // <--start
        ArrayList<String> list = stream.collect(Collector.of(
                ArrayList::new,
                ArrayList::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                })
        );
        // --end-->

        assertEquals(ArrayList.class, list.getClass());
        assertIterableEquals(
                Arrays.asList("Hello", "What", "is", "your", "name"),
                list
        );
    }
//  这个参数封装了supplier, accumulator, 和 combiner 3个操作。
//。首先创建一个集合容器；其次将元素放入集合容器中，最后进行归集操作conbiner，将多个结果集合合并为一个结果集合输出。
//    https://blog.csdn.net/qq_36372507/article/details/78818076
//    http://www.java2s.com/Tutorials/Java/Stream_How_to/Stream_Collector/Create_collector_from_supplier_accumulator_combiner_and_finisher.htm


    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_to_map() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033),
                new KeyValuePair<>("Bobs", 2094)

        ).parallel();

        // TODO: please implement toMap collector using `stream.collect`. You cannot use existing `toMap` collector.
        // <--start

//        Map<String, Integer> map1 = stream.collect(Collectors.
//                toMap(item -> item.getKey(), item -> item.getValue()));

        //       put 重复的会报错

        HashMap<String, Integer> map = stream.collect(Collector.of(
                HashMap::new,
                (s, a) -> s.put(a.getKey(), a.getValue()),
                (left, right) -> {
                    left.putAll(right);
                    return left;
                }
        ));


        // --end-->
        assertTrue(map.containsKey("Bobs"));
        assertEquals(2094, map.get("Bobs").intValue());
        assertTrue(map.containsKey("Harry"));
        assertEquals(2033, map.get("Harry").intValue());
        assertTrue(map.containsKey("Bob"));
        assertEquals(2014, map.get("Bob").intValue());
    }
/////////////？

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_to_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        );

        // TODO: implement grouping collector using `stream.collect`. You cannot use existing `groupingBy` collector.
        // <--start

        HashMap<String, List<Integer>> map = stream.collect(Collector.of(
                () -> new HashMap<>(),
                (result, item) -> {
                    if (result.containsKey(item.getKey())) {
                        List<Integer> list = result.get(item.getKey());
                        list.add(item.getValue());
                    } else {
                        ArrayList<Integer> arrayList = new ArrayList<>();
                        arrayList.add(item.getValue());
                        result.put(item.getKey(), arrayList);
                    }
                },
                (result, merge) -> {
                    merge.forEach((key, value) -> {
                        if (result.containsKey(key)) {
                            result.get(key).addAll(value);
                        } else {
                            result.put(key, value);
                        }
                    });
                    return result;
                }
        ));
        // --end-->

        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
    }

    //////////？
    @SuppressWarnings("ConstantConditions")
    @Test
    void should_collect_to_group_continued() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. This time please use `Collectors.groupingBy`
        // <--start

        Map<String, List<Integer>> map = stream.collect(
                Collectors.groupingBy(s -> s.getKey(), Collectors.mapping(s -> s.getValue(), Collectors.toList())));
        // --end-->


        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_calculate_number_in_each_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You should use `Collectors.groupingBy` and
        // TODO: downstream collector.
        // <--start
        Map<String, Long> map = stream.collect(Collectors.groupingBy(s -> s.getKey(), Collectors.counting()));
        // --end-->

        assertEquals(2, map.size());
        assertEquals(2, map.get("Harry").longValue());
        assertEquals(1, map.get("Bob").longValue());
    }
//    参数downstream还是一个收集器Collector对象

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_calculate_sum_of_each_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You should use `Collectors.groupingBy` and
        // TODO: downstream collector.
        // <--start
        Map<String, Integer> map = stream.collect(
                Collectors.groupingBy(KeyValuePair::getKey,
                        Collectors.summingInt((KeyValuePair::getValue))
                )
        );
        // --end-->

        assertEquals(2, map.size());
        assertEquals(4035, map.get("Harry").intValue());
        assertEquals(2014, map.get("Bob").intValue());
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "OptionalAssignedToNull"})
    @Test
    void should_calculate_sum_using_reduce() {
        List<Integer> numbers = new ArrayList<>();
        Stream
                .iterate(1, i -> i + 1)
                .limit(100)
                .forEach(numbers::add);

        // TODO: please modify the following code to pass the test
        // <--start
        Optional<Integer> reduced = numbers.stream().reduce((a, b) -> a + b);
        // --end-->

        //noinspection ConstantConditions
        assertEquals(5050, reduced.get().intValue());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_calculate_total_character_lengths() {
        List<String> words = Arrays.asList("The", "future", "is", "ours");

        // TODO: please calculate the total number of characters using `reduce`.
        // <--start

//      Integer total = words.stream().mapToInt(String::length).sum();
//        Integer total = words.stream().reduce(
//                        0,
//                        (prev, word) -> prev + word.length(),
//                        (total1, total2) -> total1 + total2);
//
        Integer total = words.stream().mapToInt(String::length).reduce((a, b) -> a + b).orElse(0);

        // --end-->

        assertEquals(15, total.intValue());
    }

    @SuppressWarnings({"SameParameterValue", "OptionalUsedAsFieldOrParameterType"})
    private static <T> T getValue(Optional<T> optional, T defaultValue) {
        // TODO: please implement the following method to pass the test
        // <--start
        return optional.orElse(defaultValue);
        // --end-->
    }

    private static Stream<Character> letters(String text) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < text.length(); ++i) {
            characters.add(text.charAt(i));
        }
        return characters.stream();
    }
}

class YieldOptional {
    Optional<String> get() {
        return Optional.of("Hello");
    }
}

/*
 * - Can you use `collect` method to implement `joining(String delimiter)` method?
 * - What can you do using primitive types with streams?
 */