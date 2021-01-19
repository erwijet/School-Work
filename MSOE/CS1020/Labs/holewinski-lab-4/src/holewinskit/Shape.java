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
 * Defines a shape that is drawable using the WinPlotterFX Library
 *
 * @author holewinskit
 */
public abstract class Shape {
    private Color color;
    protected final double x;
    protected final double y;

    /**
     * Creates an instance of the shape
     * @param x the x coordinate
     * @param y the y coordinate
     * @param color the javafx color to set the pen to
     */
    public Shape(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * Draw the shape with the specified plotter
     * @param plotter the plotter to use
     */
    public abstract void draw(WinPlotterFX plotter);

    /**
     * Apply the pen color to the plotter
     * @param plotter the plotter to apply the pen color to
     */
    public void setPenColor(WinPlotterFX plotter) {
        plotter.setPenColor(color.getRed(), color.getGreen(), color.getBlue());
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
