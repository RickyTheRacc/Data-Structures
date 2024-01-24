package com.gradescope.lab1pr2;

public class Problem2 {
    public static void main(String[] args){
        for (int i = 1; i <= 20; i++) {
            System.out.println(i % 2 == 0 ? i : i * 2);
        }
    }
}