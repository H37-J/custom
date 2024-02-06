package com.hjk.custom.utils.algorithms.etc.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WordLadder {

    static int count;

    public static void main(String... args) {
        var startWord = "hit";
        var endWord = "cog";
        String[] wordList = {"hot", "dot", "dog", "lot", "log", "cog"};
        var res = solution(startWord, endWord, wordList);
    }

    private static int solution(String startWord, String endWord, String[] wordList) {
        List<String> charList = new ArrayList<>(Arrays.stream(endWord.split(""))
                .toList());

        return count;
    }
}
