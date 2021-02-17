package holewinskit;

import edu.msoe.cs1021.ImageUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Lab10Controller {
    private boolean filterOpen = false;
    private Stage filterStage;

    // region def instance refs

    @FXML
    private Button filterButton;

    @FXML
    private Label lblPath;

    @FXML
    private ImageView imageView;

    public static Image transformImage(Image image, Transformable transform) {
        final int width = (int) image.getWidth();
        final int height = (int) image.getHeight();

        WritableImage raster = new WritableImage(width, height);
        PixelWriter writer = raster.getPixelWriter();
        PixelReader reader = image.getPixelReader();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                writer.setColor(x, y, transform.apply(y, reader.getColor(x, y)));
            }
        }

        return raster;
    }

    @FXML
    public void performOpen(ActionEvent e) {
        FileChooser fc = new FileChooser();
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("JPG Files", "*.jpg"));
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("MSOE Image Files", "*.msoe"));

        Path path = fc.showOpenDialog(Lab10.getPrimaryStage()).toPath();
        lblPath.setText(path.toString());

        imageView.setImage(ImageIO.read(path));
    }

    @FXML
    public void perfromSave(ActionEvent e) {
        FileChooser fc = new FileChooser();
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("JPG Files", "*.jpg"));
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("MSOE Image Files", "*.msoe"));
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("BMSOE Image Files", "BMSOE"));

        Path path = fc.showSaveDialog(Lab10.getPrimaryStage()).toPath();

        ImageIO.write(imageView.getImage(), path);
    }

    @FXML
    public void performReload(ActionEvent e) {
        if ((new File(lblPath.getText())).exists()) {
            imageView.setImage(ImageIO.read((new File(lblPath.getText())).toPath()));
        } else {
            Alerting.SimpleError(
                    "You have either not loaded a file, or your file no longer exists at the specified path");
        }
    }

    @FXML
    public void performGreyscale(ActionEvent e) {
        Image img = imageView.getImage();

        imageView.setImage(transformImage(img, (y, c) -> {
            final double r = c.getRed();
            final double g = c.getGreen();
            final double b = c.getBlue();

            final float gray = (float) ((0.2126 * r) + (0.7152 * g) + (0.0722 * b));
            return Color.color(gray, gray, gray, c.getOpacity());
        }));
    }

    @FXML
    public void performNegative(ActionEvent e) {
        Image img = imageView.getImage();

        imageView.setImage(transformImage(img, (y, c) -> {
            final double r = 1 - c.getRed();
            final double g = 1 - c.getGreen();
            final double b = 1 - c.getBlue();

            return Color.color(r, g, b, c.getOpacity());
        }));
    }

    @FXML
    private void red(ActionEvent e) {
        Image img = imageView.getImage();

        imageView.setImage(transformImage(img, (y, c) ->
             Color.color(c.getRed(), 0, 0, c.getOpacity())));
    }

    @FXML
    private void redGray(ActionEvent e) {
        Image img = imageView.getImage();

        imageView.setImage(transformImage(img, (y, c) -> {
            if (y % 2 == 0) {
                return Color.color(c.getRed(), 0, 0, c.getOpacity()); // to red only
            } else {
                // to grayscale
                final double r = c.getRed();
                final double g = c.getGreen();
                final double b = c.getBlue();

                final float gray = (float) ((0.2126 * r) + (0.7152 * g) + (0.0722 * b));
                return Color.color(gray, gray, gray, c.getOpacity());
            }
        }));
    }

    @FXML
    private void openFilter(ActionEvent e) {
        filterButton.setText(filterOpen ? "Show Filter" : "Hide Filter");

        if (!filterOpen) {

            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("filterWindow.fxml"));
                filterStage = new Stage();
                filterStage.setUserData(this);

                filterStage.setTitle("Image Manipulator | Kernel Editor");
                filterStage.setScene(new Scene(root, 403, 139));
                filterStage.show();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else {
            filterStage.close();
        }

        filterOpen = !filterOpen;
    }

    @FXML
    private void performExit(ActionEvent evt) {
        ((Stage) imageView.getScene().getWindow()) .close();
    }

    // endregion

    public void applyKernel(double[] kernel) {
        Image img = imageView.getImage();
        imageView.setImage(ImageUtil.convolve(img, kernel));
    }
}
