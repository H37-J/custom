package com.hjk.custom.utils.algorithms.etc.string;

import java.util.HashMap;

public class HorspoolSearch {

    private static HashMap<Character, Integer> shiftValues;

    private static Integer patternLength;

    private static Integer call = 0;

    private static int comparisons = 0;

    public static void main(String... args) {
        var res = res("AB", "nrowASBR");
        System.out.println(res);
    }

    private static int res(String pattern, String text){
        int patternLength = pattern.length();
        char firstChar = pattern.charAt(0);
        for(int i = 0; i < text.length(); i++) {
            if(firstChar == text.charAt(i)) {
                boolean check = true;
                for(int j = 0; j < patternLength; j++) {
                    call++;
                    if(pattern.charAt(j) != text.charAt(j + i)) {
                        check = false;
                        break;
                    }
                }
                if(check) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static int firstOccurrence(String pattern, String text, boolean caseSensitive) {
        shiftValues = calcShiftValues(pattern);
        comparisons = 0;

        if (pattern.isEmpty()) {
            return -1;
        }

        int textIndex = pattern.length() - 1;

        while (textIndex < text.length()) {
            int i = pattern.length() - 1;
            while (i >= 0) {
                comparisons++;
                char patternChar = pattern.charAt(i);
                char textChar = text.charAt((textIndex + i) - (pattern.length() - 1));
                print(patternChar, textChar);
                if (!charEquals(patternChar, textChar, caseSensitive)) {
                    System.out.println(text.charAt(textIndex));
                    int add = getShiftValue(text.charAt(textIndex));
                    System.out.println(add);
                    textIndex += add;
                    break;
                }
                i--;
            }

            if (i == -1) {
                return textIndex - pattern.length() + 1;
            }
        }

        return -1;
    }

    private static HashMap<Character, Integer> calcShiftValues(String pattern) {
        patternLength = pattern.length();
        HashMap<Character, Integer> table = new HashMap<>();

        for(int i = pattern.length() - 2; i >= 0; i--) {
            char c= pattern.charAt(i);
            int finalI = i;
            table.computeIfAbsent(c, k -> pattern.length() - 1 - finalI);
        }
        return table;
    }

    private static boolean charEquals(char c1, char c2, boolean caseSensitive) {
        if (caseSensitive) {
            return c1 == c2;
        }
        return Character.toLowerCase(c1) == Character.toLowerCase(c2);
    }

    private static Integer getShiftValue(char c) {
        if(shiftValues.get(c) != null) {
            return shiftValues.get(c);
        } else {
            return patternLength;
        }
    }

    private static void print(int val) {
        System.out.println(val);
    }

    private static void print(String val1, String val2) {
        System.out.println(val1 + "," + val2);
    }

    private static void print(char val1, char val2) {
        System.out.println(val1 + "," + val2);
    }
}


