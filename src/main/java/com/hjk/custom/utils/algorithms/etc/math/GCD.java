package com.hjk.custom.utils.algorithms.etc.math;

public class GCD {

    public static void main(String... args) {
        int[] arr = {4,12,24};

    }

    public static int gc(int num1, int num2) {
        if(num1 < 0 || num2 < 0 ){
            throw new ArithmeticException();
        }

        if(num1 == 0 || num2 == 0) {
            return Math.abs(num1 - num2);
        }

        while(num1 % num2 != 0) {
            int reminder = num1 % num2;
            num1 = num2;
            num2 = reminder;
        }
        return num2;
    }

    public static int gcd(int... numbers) {
        int result = 0;
        for(final var number: numbers){
            result = gcd(result, number);
        }
        return result;
    }
}
