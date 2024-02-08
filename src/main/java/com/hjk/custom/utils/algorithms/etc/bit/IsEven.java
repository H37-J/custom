package com.hjk.custom.utils.algorithms.etc.bit;

public class IsEven {

    public static void main(String... args) {
        int result = 0;
        result ^= 1;
        result ^= 5;
        System.out.println(result);
    }

    public static boolean isEven(int number) {
        return (number & 1) == 0;
    }
}
