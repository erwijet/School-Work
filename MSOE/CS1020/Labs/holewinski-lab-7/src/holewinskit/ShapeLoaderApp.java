/*
 * Course: CS1021 - Berisha
 * Winter 2021
 * Lab 7 - Shapes Revisted
 * Name: Tyler Holewinski
 * Created: 1/28/2021
 */

package holewinskit;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Parse a file and draw to plotter
 * @author Tyler Holewinski (@erwijet)
 */
public class ShapeLoaderApp extends Application {
    public static final int WINDOW_SIZE = 800;
    public static final int GRID_INCREMENT = WINDOW_SIZE / 10;
    public static final int HEAD_SIZE = 700;

    /**
     * Launches the JavaFX application
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method exists to comply with lab instruction
     * The actual functionality exists within Color::web.
     * @param str The input String
     * @return The color
     * @throws InputMismatchException If the string is invalid
     */
    public static Color stringToColor (String str) throws InputMismatchException {
        try {
            return Color.web(str);
        } catch (IllegalArgumentException ex) {
            // Change exception type to comply with lab instruction
            throw new InputMismatchException(ex.getMessage());
        }
    }

    /**
     * Parse shape from string
     * @param str the string to pull the information from
     * @return The shape object
     * @throws IllegalArgumentException thrown if the string is not the correct format
     */
    public static Shape parseShape(String str) throws IllegalArgumentException {
        String[] parts = str.split(":");

        try {
            String shapeCode = parts[0];
            parts = parts[1].trim().split("[ \t]+"); // regex: match any non-newline whitespace

            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            Color c = stringToColor(parts[2]);

            switch (shapeCode) {
                case "P":
                    return new Point(x, y, c);
                case "C":
                    int radius = Integer.parseInt(parts[3]);
                    return new Circle(x, y, radius, c);
                case "T":
                    int triangleBase = Integer.parseInt(parts[3]);
                    int triangleHeight = Integer.parseInt(parts[4]);
                    return new Triangle(x, y, triangleBase, triangleHeight, c);
                case "R":
                    int rectWidth = Integer.parseInt(parts[3]);
                    int rectHeight = Integer.parseInt(parts[4]);
                    return new Rectangle(x, y, rectWidth, rectHeight, c);
                case "LT":
                    int lblTriangleBase = Integer.parseInt(parts[3]);
                    int lblTriangleHeight = Integer.parseInt(parts[4]);
                    String triName = "";
                    int e = 5;
                    while (parts.length > e) {
                        triName += parts[e++];
                    }


                    return new LabeledTriangle(x, y, lblTriangleBase, lblTriangleHeight, c, triName);
                case "LR":
                    int lblRectWidth = Integer.parseInt(parts[3]);
                    int lblRectHeight = Integer.parseInt(parts[4]);
                    String rectName = "";
                    int i = 5;
                    while (parts.length > i) {
                        rectName += (" " + parts[i++]);
                    }

                    return new LabeledRectangle(x, y, lblRectWidth, lblRectHeight, c, rectName);
                default:
                    throw new RuntimeException();
            }
        } catch (RuntimeException ex) { // includes null pointer
            throw new IllegalArgumentException(
                    "invalid format! String should be in format <code>: <x> <y> <color> <base/height/radius...?>");
        }
    }

    /**
     * Consume the remainder of the scanner, and return a list of shapes
     * @param sc The scanner to consume
     * @return The list of Shapes
     */
    public ArrayList<Shape> readShapes(Scanner sc) {
        ArrayList<Shape> shapes = new ArrayList<>();

        while (sc.hasNext()) {
            try {
                shapes.add(parseShape(sc.nextLine()));
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return shapes;
    }

    /**
     * Draws an arraylist of shapes to the plotter
     * @param shapes The arraylist of Shapes
     * @param plotter The plotter to draw to
     */
    public void drawShapes(ArrayList<Shape> shapes, WinPlotterFX plotter) {
        shapes.forEach(shape -> shape.draw(plotter)); // draw each shape to plotter
    }

    /**
     * Parse a specified file and draw to the stage
     *
     * @param stage The stage to draw to
     */
    @Override
    public void start(Stage stage) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Shape Loader App | Open File");
        fc.setSelectedExtensionFilter(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fc.showOpenDialog(stage);

        if (selectedFile == null) {
            return; // quit if no file is selected
        }

        try (Scanner sc = new Scanner(selectedFile)) {
            String windowTitle = sc.nextLine();
            int width = sc.nextInt();
            int height = sc.nextInt();
            sc.nextLine(); // consume newline
            Color c = stringToColor(sc.nextLine());

            WinPlotterFX plotter = new WinPlotterFX();
            plotter.setWindowTitle(windowTitle);
            plotter.setWindowSize(width, height);
            plotter.setBackgroundColor(c.getRed(), c.getGreen(), c.getBlue());

            drawShapes(readShapes(sc), plotter);
            plotter.showPlotter();

        } catch (NoSuchElementException e) {
            (new Alert(Alert.AlertType.ERROR, "Invalid header information! Quitting...")) .showAndWait();
        } catch (FileNotFoundException e) {
            (new Alert(Alert.AlertType.ERROR, "File not found! Quitting...")) .showAndWait();
        }
    }
}

