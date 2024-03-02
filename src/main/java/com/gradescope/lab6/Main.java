package com.gradescope.lab6;

public class Main {
    public static void main(String[] args) {
        ArrayIntList list = new ArrayIntList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.stretch1();
        System.out.println(list);

        ArrayIntList list2 = new ArrayIntList();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.stretch2();
        System.out.println(list2);

        LinkedIntList list3 = new LinkedIntList();
        list3.add(1);
        list3.add(2);
        list3.add(3);
        list3.add(4);
        list3.add(5);
        list3.stretch1();
        System.out.println(list3);

        LinkedIntList list4 = new LinkedIntList();
        list4.add(1);
        list4.add(2);
        list4.add(3);
        list4.add(4);
        list4.add(5);
        list4.stretch2();
        System.out.println(list4);
    }
}
