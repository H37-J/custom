package com.hjk.custom.utils.algorithms.etc.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Palindrome {

    public static void main(String... args) {
        isPalindrome("abba");
    }

    public static boolean isPalindrome(String s) {
        if(s == null || s.length() <= 1) {
           return true;
        }

        if(s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }

        return isPalindrome(s.substring(1, s.length() - 1));
    }

}
