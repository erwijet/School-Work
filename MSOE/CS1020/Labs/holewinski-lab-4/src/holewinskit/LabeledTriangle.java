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

/**
 * A labaled extension of the Triangle class
 */
public class LabeledTriangle extends Triangle {
    private final String name;

    /**
     * Create an instance of the labaled triangle class with rectangular style coordinates
     * @param x The lower left corder x coordinate
     * @param y The lower left corner ty coordinate
     * @param base The size of the base of the triangle
     * @param height The size of the height of the triangle
     * @param color The color to set the pen to
     * @param name The text to display underneath the triangle as a label
     */
    public LabeledTriangle(double x, double y, double base, double height, Color color, String name) {
        super(x, y, base, height, color);
        this.name = name;
    }

    /**
     * Create an instance of the labaled triangle class with rectuangular style coordinates with a generic label
     * "Labaled Triangle"
     *
     * @param x The lower left corder x coordinate
     * @param y The lower left corner ty coordinate
     * @param base The size of the base of the triangle
     * @param height The size of the height of the triangle
     * @param color The color to set the pen to
     */
    public LabeledTriangle(double x, double y, double base, double height, Color color) {
        super(x, y, base, height, color);
        this.name = "Labeled Triangle";
    }

    /**
     * Draw the shape to the specified plotter
     * @param plotter the plotter to use
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        super.draw(plotter);

        final double textHeight = 10; // 20 px
        plotter.printAt(x, y - textHeight, name);
    }
}
