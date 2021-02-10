/**
 * Course: CS1021 - Berisha
 * Winter 2021
 * <holewinski-lab-9>
 * Name: Tyler Holewinski
 * Created: 02/09/2021
 */

package holewinskit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Arrays;

public class FilterWindowController {
    Lab9Controller lab8Controller;

    @FXML
    private TextField val1;
    @FXML
    private TextField val2;
    @FXML
    private TextField val3;
    @FXML
    private TextField val4;
    @FXML
    private TextField val5;
    @FXML
    private TextField val6;
    @FXML
    private TextField val7;
    @FXML
    private TextField val8;
    @FXML
    private TextField val9;

    @FXML
    private void onBlurClick (ActionEvent e) {
        val1.setText("0");
        val2.setText("1");
        val3.setText("0");
        val4.setText("1");
        val5.setText("5");
        val6.setText("1");
        val7.setText("0");
        val8.setText("1");
        val9.setText("0");
    }

    @FXML
    private void onSharpenClick (ActionEvent e) {
        val1.setText("0");
        val2.setText("-1");
        val3.setText("0");
        val4.setText("-1");
        val5.setText("5");
        val6.setText("-1");
        val7.setText("0");
        val8.setText("-1");
        val9.setText("0");
    }

    @FXML
    private void onApplyCLick (ActionEvent e) {
        double[] kernel = new double[] {
            Double.parseDouble(val1.getText()),
            Double.parseDouble(val2.getText()),
            Double.parseDouble(val3.getText()),
            Double.parseDouble(val4.getText()),
            Double.parseDouble(val5.getText()),
            Double.parseDouble(val6.getText()),
            Double.parseDouble(val7.getText()),
            Double.parseDouble(val8.getText()),
            Double.parseDouble(val9.getText())
        };

        double sum = Arrays.stream(kernel).sum();

        kernel = Arrays.stream(kernel).map(elem -> elem / sum).toArray();

        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Lab9Controller controller = (Lab9Controller) (stage.getUserData());
        controller.applyKernel(kernel);
    }
}
