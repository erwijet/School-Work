package holewinskit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Lab10 extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Lab10.primaryStage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("lab10.fxml"));
        primaryStage.setTitle("Image Manipulator");
        primaryStage.setScene(new Scene(root, 600, 427));
        primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return Lab10.primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
