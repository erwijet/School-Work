/**
 * Course: CS 1021
 * =======================
 * Name: Tyler Holewinski
 * Date: 2/2/2021
 * =======================
 * holewinskit@msoe.edu
 * gh/erwijet
 */

package holewinskit;

import javafx.scene.control.Alert;

/**
 * A java 8 Alert Wrapper for cleaner code when calling alerts
 *
 * @author Tyler Holewinski
 */
public class Alerting {
    /**
     * Create a simple AlertType.Error message and show
     * @param text The text of the Alert
     */
    public static void SimpleError(String text) {
        ( new Alert(Alert.AlertType.ERROR, text)).show();
    }
}
