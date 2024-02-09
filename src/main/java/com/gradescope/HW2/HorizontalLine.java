package com.gradescope.HW2;

import bridges.base.Color;
import bridges.base.ColorGrid;

public class HorizontalLine extends Mark {
    private final int start;
    private final int end;
    private final int y;
    private final Color c;

    public HorizontalLine(int start, int end, int y, Color c) {
        this.start = start;
        this.end = end;
        this.y = y;
        this.c = c;
    }

    @Override
    public void draw(ColorGrid cg) {
        for (int i = start; i <= end; i++) {
            cg.set(y, i, c);
        }
    }

    @Override
    public String toString() {
        return "HorizontalLine{" +
            "start=" + start +
            ", end=" + end +
            ", y=" + y +
            ", c=" + c +
            '}';
    }
}
