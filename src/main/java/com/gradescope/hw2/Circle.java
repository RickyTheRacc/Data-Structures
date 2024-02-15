package com.gradescope.hw2;

import bridges.base.Color;
import bridges.base.ColorGrid;

// This code derived from https://www.geeksforgeeks.org/mid-point-circle-drawing-algorithm/

@SuppressWarnings("unused")
public class Circle extends Mark {
    // None of these fields will ever change, so they are marked as final
    private final int radius;
    private final int xcenter;
    private final int ycenter;

    public Circle(int radius, int xcenter, int ycenter, Color color) {
        // Set the fields in the constructor
        this.radius = radius;
        this.xcenter = xcenter;
        this.ycenter = ycenter;
        this.color = color;
    }

    @Override
    public void draw(ColorGrid cg) {
        //Let r be radius of the circle
        //let xc be x coordinate of the circle
        //let yc be y coordinate of the circle
        //To draw the circle do:
        int x = radius;
        int y = 0;
        int error = 0;

        while (x >= y) {

            //draw  a point with (xc + x, yc + y) coordinates
            cg.set(xcenter + x, ycenter + y, color);
            //draw  a point with (xc + y, yc + x) coordinates
            cg.set(xcenter + y, ycenter + x, color);
            //draw a point with (xc + x, yc - y) coordinates
            cg.set(xcenter + x, ycenter - y, color);
            //draw a point with (xc + y, yc - x) coordinates
            cg.set(xcenter + y, ycenter - x, color);
            //draw a point with (xc - x, yc + y) coordinates
            cg.set(xcenter - x, ycenter + y, color);
            //draw a point with (xc - y, yc + x) coordinates
            cg.set(xcenter - y, ycenter + x, color);
            //draw a point with (xc - x, yc - y) coordinates
            cg.set(xcenter - x, ycenter - y, color);
            //draw a point with (xc - y, yc - x) coordinates
            cg.set(xcenter - y, ycenter - x, color);

            y += 1;
            error += 1 + 2 * y;

            if (2 * (error - x) + 1 > 0) {
                x -= 1;
                error += 1 - (2 * x);
            }
        }
    }
}
