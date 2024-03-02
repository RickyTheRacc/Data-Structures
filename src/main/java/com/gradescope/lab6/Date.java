package com.gradescope.lab6;

@SuppressWarnings("unused")
public class Date {
    private final int day, month;

    public Date(int day, int month) {
        this.day = day;
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public String toString() {
        return month + "/" + day;
    }

    public int daysInMonth() {
        if (month == 2) return 28;

        if (month == 1 || month == 3 || month == 5) return 31;
        if (month == 7 || month == 8 || month == 10 || month == 12) return 31;

        return 30;
    }
}
