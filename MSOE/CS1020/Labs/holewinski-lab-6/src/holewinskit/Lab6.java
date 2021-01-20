/**
 * Lab6.java
 *
 * Course: CS1021 - Winter
 * @author Tyler Holewinski
 *
 * Prof. Dr. Berisha
 * Date: 1/19/2021
 */

package holewinskit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Lab6 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("lab6.fxml"));
        primaryStage.setTitle("holewinskit Lab 6 [Web Tester]");
        primaryStage.setScene(new Scene(root, 566, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
