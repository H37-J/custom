package com.hjk.custom.utils.algorithms.etc.math;

import java.util.Arrays;

public class AbsoluteMin {

    public static void main(String... args) {
        var res = sumOfDividers(12, 12);
        System.out.println(res);
    }

    public static int getMinValue(int... numbers) {
        if (numbers.length == 0) {
            throw new IllegalArgumentException("Numbers array cannot be empty");
        }

        var absMinWrapper = new Object() {int value = numbers[0];};
        Arrays.stream(numbers).skip(1)
                .filter(number -> Math.abs(number) < Math.abs(absMinWrapper.value))
                .forEach(number -> absMinWrapper.value = number);
        return absMinWrapper.value;
    }

    public static int sumOfDividers(int number, int divisor) {
        if (divisor == 1) {
            return 0;
        } else if (number % --divisor == 0) {
            return sumOfDividers(number, divisor) + divisor;
        } else {
            return sumOfDividers(number, divisor);
        }
    }


}
