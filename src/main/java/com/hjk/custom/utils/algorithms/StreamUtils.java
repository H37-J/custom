package com.hjk.custom.utils.algorithms;


import org.springframework.cglib.core.Local;
import org.springframework.core.convert.converter.Converter;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamUtils {

    public static void main(String... args) {
        var utils = new StreamUtils();
        utils.t20();
    }

    public void t1() {
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted);
    }

    public void t2() {
        Predicate<String> predicate = (s) -> !s.isEmpty();
        predicate.test("test");

        Predicate<Boolean> nonNull = Objects::nonNull;
        ;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }

    public void t3() {
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
    }

    public void t4() {
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();
    }

    public void t5() {
        Consumer<String> print = System.out::println;
        print.accept("Hello World");

        Consumer<Person> persons = (p) -> System.out.println(p.age);
        persons.accept(new Person("hjk", 10));
    }

    public void t6() {
        Comparator<Person> comparator = (p1, p2) -> p1.name.compareTo(p2.name);

        Person p1 = new Person("a", 10);
        Person p2 = new Person("b", 13);

        var result1 = comparator.compare(p1, p2);
        var result2 = comparator.reversed().compare(p1, p2);

        System.out.println(result1);
        System.out.println(result2);
    }

    public void t7() {
        Optional<String> optional = Optional.of("test");

        var result1 = optional.isPresent();
        var result2 = optional.get();
        var result3 = optional.orElse("false");
        optional.ifPresent((s) -> System.out.println(s.charAt(0)));
    }

    public void t8() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        long count = list.stream().filter(val -> val > 2).count();
        System.out.println(count);
    }

    public void t9() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        var result = list.stream().reduce(Integer::sum).get();
        System.out.println(result);
    }

    public void t10() {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, i);
        }
//        map.forEach((key, value) -> System.out.println(key + "," + value));

        map.computeIfPresent(1, Integer::sum);
        map.computeIfAbsent(1, num -> 100);
        map.computeIfAbsent(11, num -> 11);

        map.remove(1, 1);
        map.get(1); // 2

        map.merge(1, 2, Integer::sum);
        var result = map.get(1);
    }

    public void t11() {
        Clock clock = Clock.systemDefaultZone();
        long miles = clock.millis();

        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        var result1 = zone1.getRules();

        LocalTime now1 = LocalTime.now();
        LocalTime now2 = LocalTime.now();
        var result2 = now1.isBefore(now2);

        long secondsBetween = ChronoUnit.SECONDS.between(now1, now2);

        LocalTime late = LocalTime.of(23, 59, 59);

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate yesterday = tomorrow.minusDays(2);

        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();

        DateTimeFormatter formatter =
                DateTimeFormatter
                        .ofPattern("MMM dd, yyyy - HH:mm");

        LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
        String string = formatter.format(parsed);
    }

    public String t12(String str) {
        return switch (str) {
            case "A" -> "a";
            case "B" -> "b";
            default -> str;
        };
    }

    public void t13() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        var value = list.stream()
                .filter(s -> s.equals("b")).findFirst().orElse("not found");
    }

    public void t14() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            list.add(i);
        }

        var value1 = list.stream()
                .filter(val -> val % 2 == 0)
                .findFirst()
                .orElse(-1);
    }

    public void t15() {
        List<String> names = Arrays.asList("test1", "test2", "test3");
        Stream<List<String>> list = names.stream().map(name -> Arrays.asList(name, name.toUpperCase()));
        Stream<String> map = list.flatMap(Collection::stream);

        Map<Character, List<String>> grouped = map.collect(Collectors.groupingBy(s -> s.charAt(0)));
        grouped.forEach((key, value) -> {
            System.out.println(key);
            value.forEach(System.out::println);
        });
    }

    public void t16() {
        Object obj = null;
        if (obj == null) {
            throw new NullPointerException();
        }
    }

    public void t17() {
        int[] numbers = {1, 2, 3};
        int[] find = {2, 3};
        var result = Arrays.stream(find).map((val) -> StreamUtils.findIndex(val, numbers))
                .filter(val -> val != -1)
                .toArray();
        Arrays.stream(result).forEach(System.out::println);
    }

    public void t18() {
        IntStream.range(0, 10).forEach(System.out::println);
    }

    public void t19() {
        int[] numbers = {1, 2, 3};
        int[] find = {2, 3, 1, 1, 3};
        Arrays.stream(find).forEach(val1 -> {
            int index = 0;
            for (var val2 : numbers) {
                if (val1 == val2) {
                    swap(numbers, index, index - 1);
                }
                index += 1;
            }
        });
        Arrays.stream(numbers).forEach(System.out::println);
    }

    public void t20() {
        String[] str = {"abce", "abcd", "cdx"};
        Arrays.stream(str).sorted((s1, s2) -> {
                    int compare = String.valueOf(s1.charAt(2)).compareTo(String.valueOf(s2.charAt(2)));
                    if (compare < 0) {
                        return -1;
                    } else if (compare > 0) {
                        return 1;
                    } else {
                        return s1.compareTo(s2);
                    }
                })
                .toArray(String[]::new);
    }


    public static int findIndex(int num, int[] numbers) {
        var index = 0;
        for (var val : numbers) {
            if (val == num) {
                return index;
            }
            index += 1;
        }
        return -1;
    }

    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }


}

class Person {
    String name;
    int age;

    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
