package com.gradescope.HW2;

import bridges.base.Color;
import bridges.base.ColorGrid;

public class VerticalLine extends Mark {
    private final int start;
    private final int end;
    private final int x;
    private final Color c;

    public VerticalLine(int start, int end, int x, Color c) {
        this.start = start;
        this.end = end;
        this.x = x;
        this.c = c;
    }

    @Override
    public void draw(ColorGrid cg) {
        for (int i = start; i <= end; i++) {
            cg.set(i, x, c);
        }
    }

    @Override
    public String toString() {
        return "VerticalLine{" +
            "start=" + start +
            ", end=" + end +
            ", x=" + x +
            ", c=" + c +
            '}';
    }
}
