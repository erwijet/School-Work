package holewinskit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import edu.msoe.se1021.Lab6.WebsiteTester;

public class Lab6Controller {
    @FXML
    private TextField tfHost;

    @FXML
    private TextField tfPort;

    @FXML
    private TextField tfResSize;

    @FXML
    private TextField tfElapsed;

    @FXML
    private TextField tfURL;

    @FXML
    private TextField tfTimeout;

    @FXML
    private Button btnAnalyze;

    @FXML
    private Button btnSetTimeout;

    private WebsiteTester websiteTester;

    /**
     * Called by btnSetTimeout and tfTimeout onAction
     * @param e The ActionEvent
     */
    @FXML
    private void setTimeout (ActionEvent e) {
        final String text = tfTimeout.getText();

        try {
            websiteTester.setTimeout(tfTimeout.getText());
        } catch (NumberFormatException ex) {
            new Alert (Alert.AlertType.ERROR, )
        }
    }

    /**
     * Called by btnAnalyze and tfURL onAction
     * @param e The ActionEvent
     */
    @FXML
    private void analyze (ActionEvent e) {
    }
}