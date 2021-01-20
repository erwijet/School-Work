/**
 * Jab6Controller.java
 *
 * Course: CS1021 - Winter
 * @author Tyler Holewinski
 *
 * Prof. Dr. Berisha
 * Date: 1/19/2021
 */

package holewinskit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import edu.msoe.se1021.Lab6.WebsiteTester;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.rmi.AlreadyBoundException;
import java.util.ResourceBundle;

public class Lab6Controller implements Initializable {
    @FXML
    private TextField tfHost;

    @FXML
    private TextArea taRes;

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
     *
     * Attempt to set the timeout
     * @param e The ActionEvent
     */
    @FXML
    private void setTimeout (ActionEvent e) {
        final String text = tfTimeout.getText();

        try {
            websiteTester.setTimeout(text);
        } catch (NumberFormatException ex) {
            new Alert ( Alert.AlertType.ERROR,
                    "Invalid value for timeout. Timeout must be an Integer value ")
                    .show();
        }
    }

    /**
     * Create a new instance of websiteTester
     *
     * @param location The URL location
     * @param resources The bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        websiteTester = new WebsiteTester();
    }

    /**
     * Called by btnAnalyze and tfURL onAction
     * Perform analysis
     * @param e The ActionEvent
     */
    @FXML
    private void analyze (ActionEvent e) throws IOException {
        final Alert extensionAlert = new Alert(
                Alert.AlertType.INFORMATION,
                "There has been a timeout reaching the site. Extend timeout period?",
                ButtonType.YES,
                ButtonType.CLOSE);
        extensionAlert.setTitle("Continue Waiting?");
        extensionAlert.setHeaderText("Continue Waiting?");


        final String url = tfURL.getText();
        String res = "";
        try {
            websiteTester.openURL(url);
            websiteTester.openConnection();
            websiteTester.downloadText();
            tfResSize.setText(String.valueOf(websiteTester.getSize()));
            tfElapsed.setText(String.valueOf(websiteTester.getDownloadTime()));
            tfHost.setText(websiteTester.getHostname());
            tfPort.setText(String.valueOf(websiteTester.getPort()));
            taRes.setText(websiteTester.getContent());

        } catch (MalformedURLException ex) {
            new Alert(Alert.AlertType.ERROR, "Error: the URL entered was invalid") .show();
        } catch (UnknownHostException ex) {
        } catch (SocketTimeoutException ex) {
            if (extensionAlert.showAndWait()
                    .get() == ButtonType.YES) {
                TextInputDialog tid = new TextInputDialog("10");
                try {
                    final int extension = Integer.parseInt(tfTimeout.getText()) +
                            Integer.parseInt(tid.showAndWait().get());
                    websiteTester.setTimeout(String.valueOf(extension));
                    tfTimeout.setText(String.valueOf(extension));

                    analyze(e); // attempt analysis again
                } catch (NumberFormatException ex2) {
                    new Alert ( Alert.AlertType.ERROR,
                            "Invalid value for timeout. Timeout must be a positive Integer value ")
                            .show();
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            new Alert(Alert.AlertType.INFORMATION,
                    "Error: File not found of server\n" + tfURL.getText()).show();
        } catch (IOException ioException) {
            new Alert(Alert.AlertType.WARNING, "Error reading from given file. \n\n" + ioException.toString(), ButtonType.CLOSE) .show();
        }
    }
}