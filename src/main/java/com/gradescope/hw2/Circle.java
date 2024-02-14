package com.gradescope.hw2;

import bridges.base.Color;
import bridges.base.ColorGrid;

public class Circle extends Mark {
    private final int radius;
    private final int xcenter;
    private final int ycenter;

    public Circle(int radius, int xcenter, int ycenter, Color color) {
        this.radius = radius;
        this.xcenter = xcenter;
        this.ycenter = ycenter;
        this.color = color;
    }

    @Override
    public void draw(ColorGrid cg) {
        int x = radius;
        int y = 0;

        int p = 1 - radius;

        // Print the initial point on the axes after translation
        cg.set(y + ycenter, x + xcenter, color);

        // When radius is zero, only a single point will be printed
        if (radius > 0) {
            cg.set(-y + ycenter, x + xcenter, color);
            cg.set(y + ycenter, -x + xcenter, color);
            cg.set(-y + ycenter, -x + xcenter, color);
        }

        while (x > y) {
            y++;

            if (p <= 0) {
                // Mid-point is inside or on the perimeter
                p += 2 * y + 1;
            } else {
                // Mid-point is outside the perimeter
                x--;
                p += 2 * y - 2 * x + 1;
            }

            // All the perimeter points have already been printed
            if (x < y) break;

            // Print the generated point and its reflection in the other octants after translation
            cg.set(y + ycenter, x + xcenter, color);
            cg.set(-y + ycenter, x + xcenter, color);
            cg.set(y + ycenter, -x + xcenter, color);
            cg.set(-y + ycenter, -x + xcenter, color);

            // If the generated point is on the line x = y, then the perimeter points have already been printed
            if (x != y) {
                cg.set(x + ycenter, y + xcenter, color);
                cg.set(-x + ycenter, y + xcenter, color);
                cg.set(x + ycenter, -y + xcenter, color);
                cg.set(-x + ycenter, -y + xcenter, color);
            }
        }
    }
}
