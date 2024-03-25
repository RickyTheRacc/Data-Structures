package com.gradescope.lab9;

import org.junit.Test;

public class EveryOtherTest {
    @Test
    public void test1() {
        Integer[] input = {1,2,3,4,5,6};
        Integer[] expected = {1, 3, 5};
        TestUtilities.check(expected, new EveryOther<>(0,5, input));
    }

    @Test
    public void test2() {
        Integer[] input = {5,7,9,11,13,15};
        Integer[] expected = {5,9};
        TestUtilities.check(expected, new EveryOther<>(0,2, input));
    }
}