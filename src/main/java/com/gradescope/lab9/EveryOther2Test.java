package com.gradescope.lab9;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

public class EveryOther2Test {
    @Test
    public void test1() {
        Integer[] input = {1, 2, 3, 4, 5, 6};
        Iterator<Integer> i = Arrays.asList(input).iterator();
        Integer[] expected = {1, 3, 5};
        TestUtilities.check(expected, new EveryOther2<>(i));
    }

    @Test
    public void test2() {
        String[] input = {"def", "ghi", "jkl", "mno", "pqr"};
        Iterator<String> i = Arrays.asList(input).iterator();
        String[] expected = {"def", "jkl", "pqr"};
        TestUtilities.check(expected, new EveryOther2<>(i));
    }
}
