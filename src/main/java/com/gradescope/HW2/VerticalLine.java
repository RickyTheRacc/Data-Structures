package com.gradescope.HW2;

import bridges.base.Color;
import bridges.base.ColorGrid;

public class VerticalLine extends Mark {
    // None of these fields will ever change so they are marked as final
    private final int start;
    private final int end;
    private final int x;

    public VerticalLine(int start, int end, int x, Color c) {
        // Set the fields in the constructor
        this.start = start;
        this.end = end;
        this.x = x;
        this.color = c;
    }

    @Override
    public void draw(ColorGrid cg) {
        // Loop over every y value in the range and set the color at that point
        for (int i = start; i <= end; i++) {
            cg.set(i, x, color);
        }
    }
}
