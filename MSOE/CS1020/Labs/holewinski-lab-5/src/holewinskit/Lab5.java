/**
 * CS1021 - Berisha
 * Winter 2021
 * Lab5 - Game Of Life
 * Name: Tyler Holewinski
 * Created 1/12/2021
 */

package holewinskit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Lab5 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("lab5.fxml"));
        primaryStage.setTitle("The Game of Life");
        primaryStage.setScene(new Scene(root, 400, 506));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
