package com.gradescope.hw2;

import bridges.base.ColorGrid;
import bridges.base.Color;

@SuppressWarnings("unused")
public abstract class Mark {
    // Marked as protected instead of private so that subclasses can access it
    protected Color color;

    /**
     * Test if a given Color is the same as this Mark's Color
     * @param c the Color to check against
     * @return true if and only if the given Color is the same as this Mark's Color
     */
    public boolean isColor(Color c) {
        return color.equals(c);
    }

    /**
     * Draws the mark on a colorgrid
     * @param cg the colorgrid to draw on
     */
    public abstract void draw(ColorGrid cg);
}
