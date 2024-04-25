package com.hjk.custom.utils.algorithms.etc.string;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public static void main(String... args) {
        generateParenthesis(2);
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        generateParenthesisRecursive(result, "", 0, 0, n);
        System.out.println(result);
        return result;
    }

    public static void generateParenthesisRecursive(List<String> result, String current, int open, int close, int n) {
        if(current.length() == n * 2) {
            result.add(current);
            return;
        }


        if(open < n) {
            generateParenthesisRecursive(result, current + "(", open + 1, close, n);
        }

        if(close < open) {
            generateParenthesisRecursive(result, current + ")", open, close + 1, n);
        }
    }
}
