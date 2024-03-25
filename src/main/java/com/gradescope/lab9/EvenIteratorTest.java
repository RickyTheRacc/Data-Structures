package com.gradescope.lab9;

import org.junit.Test;

public class EvenIteratorTest {
    @Test
    public void test1() {
        Integer[] input = {1,2,3,4,5,6};
        Integer[] expected = {2,4,6};
        TestUtilities.check(expected, new EvenIterator(input));
    }

    @Test
    public void test2() {
        Integer[] input = {5,7,9,11,13,15};
        Integer[] expected = {};
        TestUtilities.check(expected, new EvenIterator(input));
    }
}
