package com.gradescope.HW2;

import bridges.base.Color;
import bridges.base.ColorGrid;

public class Point extends Mark {
    // None of these fields will ever change so they are marked as final
    private final int x;
    private final int y;
    private final Color c;

    public Point(int x, int y, Color c) {
        // Set the fields in the constructor
        this.x = x;
        this.y = y;
        this.c = c;
    }

    @Override
    public void draw(ColorGrid cg) {
        // Who tf decided it should be y then x first, I will have their head
        // Sets the color at the given x and y in the colorgrid
        cg.set(y, x, c);
    }
}
