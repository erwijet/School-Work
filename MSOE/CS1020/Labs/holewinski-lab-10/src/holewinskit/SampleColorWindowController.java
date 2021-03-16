/**
 * Course: CS1021 - Berisha
 * Winter 2021
 * <holewinski-lab-10>
 * Name: Tyler Holewinski
 * Created: 02/16/2021
 */

package holewinskit;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class SampleColorWindowController {
    @FXML
    private Label lblRGB;

    @FXML
    private Label lblHex;

    @FXML
    private Rectangle rectSwatch;

    public void setColor (Color color) {
        if (lblRGB == null) return; // controller could be called before window is loaded

        final int r = (int) Math.floor(color.getRed() * 255);
        final int g = (int) Math.floor(color.getGreen() * 255);
        final int b = (int) Math.floor(color.getBlue() * 255);

        lblRGB.setText("(" + r + ", " + g + ", " + b + ")");
        lblHex.setText(ImageIO.toHexString(color));
        rectSwatch.setFill(color);
    }
}
