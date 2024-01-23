package com.hjk.custom.utils.algorithms.restaurant;

import java.util.ArrayList;

public class Restaurant {

    public static void main(String... args) throws Exception {
        Table table = new Table();
        new Thread(new Cook(table), "COOK1").start();
        new Thread(new Customer(table, "apple"), "cus1").start();
        new Thread(new Customer(table, "banana"), "cus2").start();

        Thread.sleep(5000);
        System.exit(0);
    }
}


class Table {
    String[] dishNames = {"apple", "banana", "apple"};
    final int MAX_FOOD = 6;
    private ArrayList<String> dishes = new ArrayList<>();

    public synchronized void add(String dish) {
        if (dishes.size() > MAX_FOOD) {
            return;
        }

        dishes.add(dish);
        System.out.println("Dishes:" + dishes.toString());
    }

    public boolean remove(String dishName) {
        synchronized (this) {
            while (dishes.isEmpty()) {
                String name = Thread.currentThread().getName();
                System.out.println(name + " is waiting");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int i = 0; i < dishes.size(); i++) {
                if(dishName.equals(dishes.get(i))) {
                    dishes.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public int size() {
        return dishNames.length;
    }
}

class Customer implements Runnable {
    private final Table table;
    private final String food;

    Customer(Table table, String food) {
        this.table = table;
        this.food = food;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String name = Thread.currentThread().getName();

            if (eatFood()) {
                System.out.println(name + " ate a" + food);
            } else {
                System.out.println(name + " failed to eat. :(");
            }
        }
    }

    boolean eatFood() {
        return table.remove(food);
    }
}

class Cook implements Runnable {
    private final Table table;

    Cook(Table table) {
        this.table = table;
    }

    public void run() {
        while (true) {
            int index = (int) (Math.random() * table.size());
            table.add(table.dishNames[index]);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}