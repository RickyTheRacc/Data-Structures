package com.gradescope.hw2;

import bridges.base.Color;
import bridges.base.ColorGrid;

@SuppressWarnings("unused")
public class Point extends Mark {
    // None of these fields will ever change so they are marked as final
    private final int x;
    private final int y;

    public Point(int x, int y, Color c) {
        // Set the fields in the constructor
        this.x = x;
        this.y = y;
        this.color = c;
    }

    @Override
    public void draw(ColorGrid cg) {
        // I will have the head of whoever decided it should be y, x
        // Sets the color at the given x and y in the colorgrid
        cg.set(y, x, color);
    }
}
