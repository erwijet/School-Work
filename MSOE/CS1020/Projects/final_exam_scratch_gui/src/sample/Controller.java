package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;

public class Controller {
    @FXML
    private TextField numItems;
    @FXML
    private TextField cost;

    @FXML
    private Label resLbl;

    @FXML
    public void ok(ActionEvent e) {
        try {
            final int quantity = Integer.parseInt(numItems.getText());
            final float pricePerItem = Float.parseFloat(cost.getText());

            final float result = (quantity * pricePerItem);

            resLbl.setText("$" + result);
        } catch (NumberFormatException ex) {
            (new Alert(Alert.AlertType.ERROR, "Invalid Number!")).show();
        }
    }

    @FXML
    public void clear(ActionEvent e) {
        numItems.clear();
        cost.clear();
        resLbl.setText("$0.00");
    }
}
