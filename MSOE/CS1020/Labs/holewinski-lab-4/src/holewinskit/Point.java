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
 * A drawable point shape
 *
 * @author holewinskit
 */
public class Point extends Shape {
    public Point(double x, double y, Color color) {
        super(x, y, color);
    }

    /**
     * Draw the shape using a specified plotter
     * @param plotter the plotter to use
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        setPenColor(plotter);
        plotter.drawPoint(x, y);
    }
}
