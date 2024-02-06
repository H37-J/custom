package com.hjk.custom;

import com.hjk.custom.utils.algorithms.Algos;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AlgorithmsTest {

    @Test
    public void Test1() {
        assertTrue(Algos.anagrams("test", "stet"));
    }
}
