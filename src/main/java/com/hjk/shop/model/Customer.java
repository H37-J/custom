package com.hjk.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Customer {

    private String name;

    private Integer money;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(",").append(money);
        return sb.toString();
    }

    @Builder
    public Customer(String name, Integer money) {
        this.name = name;
        this.money = money;
    }

    public static void  main(String ... args) throws Exception {
        Customer customer = Customer.builder().name("hjk").money(10000).build();
        System.out.println(customer);


    }
}
