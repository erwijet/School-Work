/*
 * Course: CS1021 - Berisha
 * Winter 2021
 * Lab 4 - Inheritance with Shapes
 * Name: Tyler Holewinski
 * Created: 1/7/2021
 */

package holewinskit;

import edu.msoe.winplotterfx.WinPlotterFX;

import java.util.ArrayList;

/**
 * Enable the sequencing of draw commands to edu.msoe.winplotterfx.WinPlotterFX
 *
 * @author holewinskit
 */
public class DrawSequencer implements AutoCloseable {

    /**
     * Internal storage solution for storing two double values as an (X, Y) pair
     *
     * @author holewinskit
     */
    private static class DoublePoint {
        double x;
        double y;

        /**
         * Create an instance of the DoublePoint class
         *
         * @param x The x double to store
         * @param y The y double to store
         */
        public DoublePoint(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private WinPlotterFX plotter;
    private ArrayList<DrawSequencer.DoublePoint> points;

    /**
     * Create a new instance of the DrawSequencer class
     * @param plotter The WinPlotterFx plotter
     * @return the new DrawSequencer
     */
    public static DrawSequencer Factory(WinPlotterFX plotter) {
        return new DrawSequencer(plotter);
    }

    /**
     * Create a new instance of the DrawSequencer class
     * @param plotter the WinPlotterFx plotter
     */
    public DrawSequencer(WinPlotterFX plotter) {
        this.plotter = plotter;
        points = new ArrayList<>();
    }

    /**
     * Add an x, y coordinate to the sequence
     *
     * @param x The x value of the coordinate
     * @param y The y value of the coordinate
     * @return The DrawSequencer instance
     */
    public DrawSequencer sequence(double x, double y) {
        points.add(new DoublePoint(x, y));

        // enable chainable methods
        return this;
    }

    /**
     * Define a start position for the sequence.
     *
     * This point will simply be placed at the beginning of the sequence
     * @param x The x value of the coordinate
     * @param y The y value of the cooridante
     * @return The DrawSequencer instance
     */
    public DrawSequencer setStart(double x, double y) {
        points.add(0, new DoublePoint(x, y));
        return this;
    }

    /**
     * Run the sequence, drawing the points defined in the sequence.
     * The function will not run if the sequence is empty.
     */
    public void run() {
        if (points.isEmpty())
            return;

        // start position

        DoublePoint start = points.remove(0);
        plotter.moveTo(start.x, start.y);

        // perform draw

        points.forEach(point -> plotter.drawTo(point.x, point.y));
    }

    @Override
    public void close() {
        plotter.close();
        points = null;

        // request garbage collection to free memory
        System.gc();
    }
}
