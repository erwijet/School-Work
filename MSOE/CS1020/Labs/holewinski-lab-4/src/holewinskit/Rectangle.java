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
 * A drawable Rectangle shape
 *
 * @author holewinskit
 */
public class Rectangle extends Shape {
    protected final double height;
    protected final double width;

    /**
     * Create an instance of the rectangular class with lower left corner style rectangular parameters
     * @param x The lower left corner x coordinate
     * @param y The lower left corner y coordiante
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     * @param color The color to set the pen to
     */
    public Rectangle(double x, double y, double width, double height, Color color) {
        super (x, y, color);

        this.width = width;
        this.height = height;
    }

    /**
     * Draw the shape with the specified plotter
     * @param plotter the plotter to use
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        setPenColor(plotter); // ensure pen has been set

        DrawSequencer.Factory(plotter)
                .setStart(x, y)
                .sequence(x + width, y)
                .sequence(x + width, y + height)
                .sequence(x, y + height)
                .sequence(x, y)
                .run();
    }
}
