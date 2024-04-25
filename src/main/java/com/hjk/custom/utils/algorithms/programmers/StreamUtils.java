package com.hjk.custom.utils.algorithms.programmers;

import lombok.Setter;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.hjk.custom.utils.algorithms.programmers.Utils.*;
public class StreamUtils {

    public static void main(String... args) {
        stream7();
    }

    public static void stream1() {
        int[] array = {1, 2, 3, 4, 5, 6};
        Arrays.stream(array).mapToObj(a -> new int[]{a, a + 1, a + 2})
                .forEach(val -> System.out.println(val));
    }

    public static void stream2() {
        BiFunction<Integer, Integer, Integer> add = Integer::sum;
        int res = add.apply(1, 2);
        System.out.println(res);
    }

    public static void stream3() {
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4);
        numbers.takeWhile(val -> val != 3).forEach(System.out::println);
    }

    public static void stream4() {
        Comparator<Integer> integerComparator = Comparator.naturalOrder();
        BinaryOperator<Integer> max = BinaryOperator.maxBy(integerComparator);
        var res = max.apply(1, 2);
        System.out.println(res);
    }

    public static void stream5() {
        BiPredicate<Integer, String> predicate = (val1, val2) -> {
            return val1 == 3 && val2.equals("test");
        };
        var res = predicate.test(3, "test");
        System.out.println(res);
    }

    public static void stream6() {
        Stream<String> stream = Stream.of("a", "b", "c");
        String[] arr = stream.flatMap(val -> Stream.of(val.split(""))).toArray(String[]::new);
        System.out.println(Arrays.toString(arr));
    }

    public static void stream7() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        var res = IntStream.range(0, 4).boxed().
                flatMap(val -> {
                    if (val >= 1) val += val;
                    return Stream.of(new Integer[][]{{arr[val], arr[val + 1]}});
                }).
                toArray(Integer[][]::new);
        print(res);

        var copy = Arrays.copyOfRange(arr, 1, 4);
    }

    public static void stream8() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<Integer> result = numbers.stream()
                .mapMulti((num, consumer) -> {
                    consumer.accept(num * num);
                    consumer.accept((int) Math.sqrt(num));
                }).mapToInt(i -> (int) i)
                .boxed().collect(Collectors.toList());

        System.out.println(result);
    }

    public static void stream9() {
        Function<Integer, String> converter = String::valueOf;
        String res = converter.apply(1);
    }

    public static void stream10() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
        stream.peek(System.out::println);
    }

    //        48 ~ 57 digit 0 ~ 9
    public static void stream11() {
        String str = "1a345t0123900aa4";
        var answer = "";
        var sum = 0;
        var arr = str.split("");
        for (var s : arr) {
            if (s.codePointAt(0) >= 48 && s.codePointAt(0) <= 57) {
                answer += s;
                sum += Integer.parseInt(s);
            }
        }
        print(answer, sum);
    }

    public static void stream12() {
        int[] arr1 = {1, 2, 3};
        Integer[][] arr2 = {{1, 2}, {3, 4}};
        String[] str = {"test1", "test2"};
        var res1 = Arrays.stream(arr1).average().getAsDouble();
        var res2 = Arrays.stream(str).mapMulti((val, consumer) -> {
            consumer.accept(val.toUpperCase());
            consumer.accept(val.toLowerCase());
        }).collect(Collectors.toList());

        Integer[] res3 = Arrays.stream(arr2)
                .flatMap(Arrays::stream).toArray(Integer[]::new);

        print(res1);
        print(res2);
        print(res3);
    }

    public static void stream13() {
        //소수찾기
        int size = 30;
        IntStream.range(2, size + 1).filter(val -> {
            for (int i = 2; i <= val; i++) {
                if (val % i == 0 && val == i) {
                    return true;
                } else if (val % i == 0) {
                    return false;
                }
            }
            return false;
        }).forEach(System.out::println);
    }

    public static void stream14() {
        int size = 10;
        Random random = new Random();
        var users = IntStream.range(1, size + 1)
                .mapToObj(val -> new User(val, "hjk" + val, random.nextInt(10 + 1)))
                .toList();

        AtomicInteger sum = new AtomicInteger();
        var map = users.stream()
                .peek(user -> {
                    var score = user.getScore();
                    int rank = 1;
                    for (var compare : users) {
                        if (score < compare.getScore()) {
                            rank += 1;
                        }
                    }
                    sum.addAndGet(user.getScore());
                    user.setRank(rank);
                })
                .collect(Collectors.groupingBy(User::getRank));
        print(map);
        print("sum", sum.get());
        print("avg", sum.get() / (double) size);

        Integer num = 1;

    }

    public static void stream15() {
        int[] arr = {1, 2, 3, 4};
        int[] find = {2, 3};
        int[] filter = {2, 4};
        int[] filterIndex = {0, 3};

        var index = Arrays.stream(find).map(val -> findIndex(arr, val)).toArray();
        var filtered = Arrays.stream(arr).filter(val1 -> {
            for (var val2 : filter) {
                if (val1 == val2) {
                    return true;
                }
            }
            return false;
        }).toArray();

        List<Integer> filteredByIndex = new ArrayList<>();
        IntStream.range(0, arr.length)
                .forEach(index1 -> {
                    for (var index2 : filterIndex) {
                        if (index1 == index2) {
                            filteredByIndex.add(arr[index1]);
                            break;
                        }
                    }
                });

        print(index);
        print(filtered);
        print(filteredByIndex);
    }


    public static class User {
        int age;
        String name;

        int score;

        @Setter
        int rank;

        public User() {

        }

        public User(int age, String name, int score, int rank) {
            this.age = age;
            this.name = name;
            this.score = score;
            this.rank = rank;
        }

        public User(int age, String name, int score) {
            this.age = age;
            this.name = name;
            this.score = score;
        }

        public User age(int age) {
            this.age = age;
            return this;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        public int getRank() {
            return rank;
        }

        public User name(String name) {
            this.name = name;
            return this;
        }

        public static User builder() {
            return new User();
        }

        @Override
        public String toString() {
            return this.name + "," + this.age + "," + this.score;
        }

    }
}
