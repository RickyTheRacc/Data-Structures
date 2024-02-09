package com.gradescope.HW2;

import bridges.base.Color;
import bridges.base.ColorGrid;

public class HorizontalLine extends Mark {
    private final int start;
    private final int end;
    private final int y;

    public HorizontalLine(int start, int end, int y, Color c) {
        this.start = start;
        this.end = end;
        this.y = y;
        this.color = c;
    }

    @Override
    public void draw(ColorGrid cg) {
        for (int i = start; i <= end; i++) {
            cg.set(y, i, color);
        }
    }

    @Override
    public String toString() {
        return "HorizontalLine{" +
            "start=" + start +
            ", end=" + end +
            ", y=" + y +
            ", c=" + color +
            '}';
    }
}
