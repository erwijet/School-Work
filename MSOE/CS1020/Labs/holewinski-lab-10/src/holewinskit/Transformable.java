/**
 * Course: CS1021 - Berisha
 * Winter 2021
 * <holewinski-lab-9>
 * Name: Tyler Holewinski
 * Created: 02/09/2021
 */

package holewinskit;

import javafx.scene.paint.Color;

@FunctionalInterface
public interface Transformable {
    Color apply(int y, Color pixColor);
}