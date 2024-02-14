package com.gradescope.hw2;

import bridges.base.Color;
import bridges.base.ColorGrid;

public class HorizontalLine extends Mark {
    // None of these fields will ever change so they are marked as final
    private final int start;
    private final int end;
    private final int y;

    public HorizontalLine(int start, int end, int y, Color c) {
        // Set the fields in the constructor
        this.start = start;
        this.end = end;
        this.y = y;
        this.color = c;
    }

    @Override
    public void draw(ColorGrid cg) {
        // Loop over every x value in the range and set the color at that point
        for (int i = start; i <= end; i++) {
            cg.set(y, i, color);
        }
    }
}
