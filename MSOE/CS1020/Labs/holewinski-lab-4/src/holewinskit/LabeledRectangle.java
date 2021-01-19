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
 * An extension of the Rectangle class that draws a label underneath the Rectangle
 *
 * @author holewinskit
 */
public class LabeledRectangle extends Rectangle {
    private final String name;

    /**
     * Create an instance of the Labeled Rectangle class
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     * @param color The color to set the pen to
     * @param name The label to draw underneath the Shape
     */
    public LabeledRectangle(double x, double y, double width, double height, Color color, String name) {
        super(x, y, width, height, color);
        this.name = name;
    }

    /**
     * Create an instance of the Labled Rectangle class with a generic label "Labeled Rectangle"
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     * @param color The color to set the pen to
     */
    public LabeledRectangle(double x, double y, double width, double height, Color color) {
        super(x, y, width, height, color);
        this.name = "Labeled Rectangle";
    }

    /**
     * Draw the shape using the specified plotter
     *
     * @param plotter The plotter to use
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        super.draw(plotter);

        final double textHeight = 10; // 20 px
        plotter.printAt(x, y - textHeight, name);
    }
}
