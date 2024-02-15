package com.gradescope.hw2;

import bridges.base.Color;
import bridges.base.ColorGrid;

// This code derived from https://www.geeksforgeeks.org/bresenhams-line-generation-algorithm/

@SuppressWarnings("unused")
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
        //Let x0 and y0 be start point coordinates
        //Let x1 and y1 be end point coordinates

        int xDirection, yDirection;
        int dx = Math.abs(xEnd - xStart);
        int dy = -Math.abs(yEnd - yStart);

        int error = dx + dy;
        int error2;

        if (xStart < xEnd) {
            xDirection = 1;
        }
        else {
            xDirection = -1;
        }
        if (yStart < yEnd) {
            yDirection = 1;
        }
        else {
            yDirection = -1;
        }

        int x = xStart;
        int y = yStart;

        //draw  a point with (x,y) coordinates
        cg.set(y, x, color);

        while (x != xEnd || y != yEnd) {
            error2 = error * 2;

            if (x == xEnd) {
                y += 1;
                //draw  a point with (x,y) coordinates
                cg.set(y, x, color);
                continue;
            }

            if (y == yEnd) {
                x += 1;
                //draw  a point with (x,y) coordinates
                cg.set(y, x, color);
                continue;
            }

            if (error2 >= dy) {
                error += dy;
                x += xDirection;
            }

            if (error2 <= dx) {
                error += dx;
                y += yDirection;
            }

            //draw  a point with (x,y) coordinates
            cg.set(y, x, color);
        }
    }

//    public void oldDraw(ColorGrid cg) {
//        // Slope is rise over run, so the change in y over the change in x
//        double slope = (double) (yEnd - yStart) / (xEnd - xStart);
//
//        // The equation of a line is y = mx + b, where m is the slope and b is the y-intercept
//        // Using this we can reverse the equation to find b, y - mx = b
//        double yIntercept = yStart - slope * xStart;
//
//        // Loop through the x values from xStart to xEnd
//        for (int x = xStart; x <= xEnd; x++) {
//            // Calculate the y value using the equation of a line
//            int y = (int) Math.round(slope * x + yIntercept);
//            cg.set(y, x, color);
//        }
//    }
}
