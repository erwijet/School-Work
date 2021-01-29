/*
 * Course: CS1021 - Berisha
 * Winter 2021
 * Lab 4 - Inheritance with Shapes
 * Name: Tyler Holewinski
 * Created: 1/7/2021
 */

package holewinskit;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;

import static java.lang.Math.PI;
import static java.lang.Math.sin;
import static java.lang.Math.cos;

/**
 * A drawable circle Shape
 *
 * @author holewinskit
 */
public class Circle extends Shape {
    private static final double TWO_PI = PI * 2;

    private final double radius;

    /**
     * Creates a drawable Circle shape using polar-style coordinates
     * @param x the x coordinate (center)
     * @param y the y coordinate (center)
     * @param radius the radius of the circle
     * @param color the javafx color to set the pen to
     */
    public Circle(double x, double y, double radius, Color color) {
        super(x, y, color);
        this.radius = radius;
    }

    /**
     * Creates a drawable Circle shape from traditional rectangular values
     *
     * The value of the radius will be the half the average of height and width.
     * The x and y coordinates will be set to half the width and height, respectively, offset by the provided x and y
     * to adjust from rectangular coordinates to circular, polar coordinates.
     *
     * Please note: The values of width and height must be equal. In the event of a difference, the two will both be
     * set to the average of the coupled sum.
     *
     * @param x The x offset
     * @param y the y offset
     * @param width The width of the circle
     * @param height The height of the circle.
     * @param color The javafx color to set the pen to
     */
    public Circle(double x, double y, double width, double height, Color color) {
        this(
                x + width / 2,
                y + height / 2,
                // define radius as either the width, if width equals height, or the average of the height and width
                // if the two values differ
                (int) width == (int) height ?
                    width / 2 :
                    (width + height) / 4,
                color
        );
    }

    /**
     * Draw the circle using a specified plotter
     *
     * @param plotter the plotter to use
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        setPenColor(plotter);

        final double steps = 500f;
        final double dt = TWO_PI / steps; // let dt be the change in t each pas;

        DrawSequencer sequencer = new DrawSequencer(plotter);

        // *NOTE* Max value for t is TWO_PI plus an additional dt to allow for the connection of the circle

        for (double t = 0 ; t <= TWO_PI + dt; t += dt) {
            final double thisX = x + radius * cos(t);
            final double thisY = y + radius * sin(t);

            sequencer.sequence(thisX, thisY);
        }

        sequencer.run();
    }
}
