package com.gradescope.lab4;

public class Lab4 {
    public static void main(String[] args) {
        ArrayIntList test = new ArrayIntList();

        for (int i = 1; i <= 10; i++) {
            test.add(i);
        }

        test.add(1);
        test.add(5);
        test.add(5);

        System.out.println(test);

        test.removeAll(5);

        System.out.println(test);

        System.out.println(test.runningTotal());
    }
}
