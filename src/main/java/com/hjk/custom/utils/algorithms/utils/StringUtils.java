package com.hjk.custom.utils.algorithms.utils;

public class StringUtils {

    public static void main(String... args) {
        var res = subString("test", 0, 1);
    }

    public static char[] toCharArray(String s) {
        return s.toCharArray();
    }

    public static String newString(char[] chars) {
        return new String(chars);
    }

    public static String subString(String s, int start, int end) {
        return s.substring(start, end);
    }

}
