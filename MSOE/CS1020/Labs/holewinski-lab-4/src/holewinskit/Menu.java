/*
 * Course: CS1021 - Berisha
 * Winter 2021
 * Lab 4 - Inheritance with Shapes
 * Name: Tyler Holewinski
 * Created: 1/7/2021
 */

package holewinskit;

import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * A helper class to store setup functions relating to the menu.
 *
 * @author holewinskit
 */
public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prompt the user for a shapeid
     *
     * @return The user-entered integer, which is unbounded by the set of valid shapeids (1-5 inclusive)
     */
    public static int doPrompt() {
        Arrays.stream(new String[] {
                "==================================",
                "Choose shape for face",
                "==================================",
                "1. Rectangle",
                "2. Triangle",
                "3. Circle",
                "4. Labeled Rectangle",
                "5. Labeled Triangle",
                "6. Random",
                "=================================="
        }) .forEachOrdered(out::println);
        out.print("Selection: ");
        int selection = scanner.nextInt();
        scanner.nextLine();

        return selection;
    }

    /**
     * Generate a shape based on the shape id table:
     *
     * 1: Rectangle
     * 2: Triangle
     * 3: Circle
     * 4: Labeled Rectangle
     * 5: Labeled Triangle
     *
     * Any value outside these bounds will be set to a value within the bounds at random.
     *
     * @param id The shapeid to use
     * @param name The name of the shape, in the event the shape is labeled
     * @param x The rectangular-style x coordinate
     * @param y The recuangular-style y coordinate
     * @param width The rectangular-style width
     * @param height The rectangular-style height
     * @param color The height to set the pen to
     * @return The generated shape
     */
    public static Shape createShapeByShapeId(
            int id,
            String name,
            double x,
            double y,
            double width,
            double height,
            Color color) {
        switch(id) {
            case 1:
                return new Rectangle(x, y, width, height, color);
            case 2:
                return new Triangle(x, y, width, height, color);
            case 3:
                return new Circle(x, y, width, height, color);
            case 4:
                return new LabeledRectangle(x, y, width, height, color, name);
            case 5:
                return new LabeledTriangle(x, y, width, height, color, name);
            default:
                // recurse with random, bounded value for shapeID
                return createShapeByShapeId(new Random().nextInt(6), name, x, y, width, height, color);
        }
    }
}
