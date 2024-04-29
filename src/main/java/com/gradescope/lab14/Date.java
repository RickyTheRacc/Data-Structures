package com.gradescope.lab14;

@SuppressWarnings("unused")
public class Date implements Comparable<Date> {
    private final int year;
    private final int month;
    private final int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    @Override
    public int compareTo(Date date) {
        // Compare years first
        if (year < date.getYear()) return -1;
        else if (year > date.getYear()) return 1;

        // If years are equal, compare months
        if (month < date.getMonth()) return -1;
        else if (month > date.getMonth()) return 1;

        // If months are equal, compare days
        if (day < date.getDay()) return -1;
        else if (day > date.getDay()) return 1;

        // If all components are equal, return 0
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%04d/%02d/%02d", year, month, day);
    }

    @Override
    public int hashCode() {
        return day + (31 * month) + (961 * year);
    }

    public int hashCode2() {
        return toString().hashCode();
    }
}
