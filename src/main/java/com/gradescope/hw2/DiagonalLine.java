package com.gradescope.hw2;

import bridges.base.Color;
import bridges.base.ColorGrid;

// This code derived from https://www.geeksforgeeks.org/bresenhams-line-generation-algorithm/

public class DiagonalLine extends Mark {
    // Fields don't change so they can be final
    private final int xStart;
    private final int yStart;
    private final int xEnd;
    private final int yEnd;

    public DiagonalLine(int xStart, int yStart, int xEnd, int yEnd, Color color) {
        // Initialize the fields in the constructor
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.color = color;
    }

    @Override
    public void draw(ColorGrid cg) {
        // Slope is rise over run, so the change in y over the change in x
        double slope = (double) (yEnd - yStart) / (xEnd - xStart);

        // The equation of a line is y = mx + b, where m is the slope and b is the y-intercept
        // Using this we can reverse the equation to find b, y - mx = b
        double yIntercept = yStart - slope * xStart;

        // Loop through the x values from xStart to xEnd
        for (int x = xStart; x <= xEnd; x++) {
            // Calculate the y value using the equation of a line
            int y = (int) Math.round(slope * x + yIntercept);
            cg.set(y, x, color);
        }
    }
}
