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
 * Defines a drawable triangle shape
 *
 * @author holewinskit
 */
public class Triangle extends Shape {
    protected final double base;
    protected final double height;

    /**
     * Create an instance of the triangle class using traditional rectangular coordinates.
     *
     * @param x The x position of the lower left tip
     * @param y the y position of the lower left tip
     * @param base The size of the base
     * @param height The size of the height
     * @param color The javafx color to set the pen to
     */
    public Triangle(double x, double y, double base, double height, Color color) {
        super (x, y, color);
        this.base = base;
        this.height = height;
    }

    /**
     * Draw the shape using the specified plotter
     * @param plotter the plotter to use
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        setPenColor(plotter);

        DrawSequencer.Factory(plotter)
                .setStart(x, y)
                .sequence(x + base, y)
                .sequence(x + (base / 2), y + height)
                .sequence(x, y)
                .run();
    }
}
