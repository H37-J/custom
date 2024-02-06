package com.hjk.custom.utils.algorithms.clazz.shop;

import lombok.Getter;
import lombok.ToString;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ToString
@Getter
public class Customer {

    public static void main(String... args) {
        Stream<Customer> stream = Stream.of(
                new Customer(10, "male", SEX.MALE),
                new Customer(11, "male", SEX.MALE),
                new Customer(14, "male", SEX.MALE),
                new Customer(10, "male", SEX.MALE),
                new Customer(10, "fem", SEX.MALE));
//        Map<Boolean, Long> map1 = stream.collect(Collectors.partitioningBy(c -> c.getSex() == SEX.MALE, Collectors.counting()));
//        Map<Boolean, List<Customer>> map2 = stream.collect(Collectors.partitioningBy(c -> c.getSex() == SEX.MALE));
//        Map<Boolean, Map<Boolean, List<Customer>>> map3 = stream.collect(
//                Collectors.partitioningBy(c -> c.getSex() == SEX.MALE,
//                Collectors.partitioningBy(c -> c.getAge() > 10)));
        Map<SEX, Map<String, Customer>> map4 = stream.collect(
                Collectors.groupingBy(Customer::getSex,
                        Collectors.groupingBy(Customer::getName,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparingInt(Customer::getAge)), Optional::get))));

        System.out.println(map4);
    }

    private final int age;

    private final String name;

    private final SEX sex;

    public Customer(int age, String name, SEX sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }
}


@Getter
enum SEX {
    MALE("MALE"), FEMALE("FEMALE");

    private String sex;

    SEX(String sex) {
        this.sex = sex;
    }
}
