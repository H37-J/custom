package com.hjk.custom.utils.algorithms.clazz;

public class Account {

    public static void main(String... args) {
        Runnable run = new Run1();
        run.run();
    }

    private int balance = 1000;

    public int getBalance() {
        return balance;
    }

    public synchronized void withDraw(int money) {
        if (balance >= money) {
            try {
                Thread.sleep(1000);
                balance -= money;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Run1 implements Runnable {
    Account acc = new Account();

    public void run() {
        while (acc.getBalance() > 0) {
            int money = ((int) Math.random() * 3 + 1) * 100;
            acc.withDraw(money);
            System.out.println(acc.getBalance());
        }
    }
}

