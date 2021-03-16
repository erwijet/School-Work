package holewinskit;

import edu.msoe.cs1021.ImageUtil;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Lab10Controller {
    private Stage filterStage;
    private SampleColorWindowController smplCtl;

    // region def instance refs

    @FXML
    private Label lblPath;

    @FXML
    private Label lblStatus;

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
        lblStatus.setText("[Opening...] (working)");

        FileChooser fc = new FileChooser();
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("JPG Files", "*.jpg"));
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("MSOE Image Files", "*.msoe"));

        File file = fc.showOpenDialog(Lab10.getPrimaryStage());
        if (file == null) {
            // open file dialog canceled

            lblStatus.setText("[Open File Stopped] (ready)");
            return;
        }

        Path path = file.toPath();


        lblPath.setText(path.toString());

        imageView.setImage(ImageIO.read(path));
        lblStatus.setText("[Opened File] (ready)");
    }

    @FXML
    public void perfromSave(ActionEvent e) {
        lblStatus.setText("[Saving file...] (working)");
        FileChooser fc = new FileChooser();
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("JPG Files", "*.jpg"));
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("MSOE Image Files", "*.msoe"));
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("BMSOE Image Files", "BMSOE"));

        Path path = fc.showSaveDialog(Lab10.getPrimaryStage()).toPath();

        ImageIO.write(imageView.getImage(), path);
        lblStatus.setText("[Saved File] (ready)");
    }

    @FXML
    public void performReload(ActionEvent e) {
        lblStatus.setText("[Reloading...] (working)");
        if ((new File(lblPath.getText())).exists()) {
            imageView.setImage(ImageIO.read((new File(lblPath.getText())).toPath()));
            lblStatus.setText("[Reloaded File] (ready)");
        } else {
            Alerting.SimpleError(
                    "You have either not loaded a file, or your file no longer exists at the specified path");
            lblStatus.setText("[Reload Failed :/] (ready)");
        }
    }

    @FXML
    public void performGreyscale(ActionEvent e) {
        lblStatus.setText("[Greyscale - Transforming...] (working)");
        Image img = imageView.getImage();

        imageView.setImage(transformImage(img, (y, c) -> {
            final double r = c.getRed();
            final double g = c.getGreen();
            final double b = c.getBlue();

            final float gray = (float) ((0.2126 * r) + (0.7152 * g) + (0.0722 * b));
            return Color.color(gray, gray, gray, c.getOpacity());
        }));

        lblStatus.setText("[Greyscale - Applied] (ready)");
    }

    @FXML
    public void performNegative(ActionEvent e) {
        lblStatus.setText("[Negative - Transforming...] (working)");
        Image img = imageView.getImage();

        imageView.setImage(transformImage(img, (y, c) -> {
            final double r = 1 - c.getRed();
            final double g = 1 - c.getGreen();
            final double b = 1 - c.getBlue();

            return Color.color(r, g, b, c.getOpacity());
        }));

        lblStatus.setText("[Negative - Applied] (ready)");
    }

    @FXML
    private void red(ActionEvent e) {
        lblStatus.setText("[Red - Transforming...] (working)");
        Image img = imageView.getImage();

        imageView.setImage(transformImage(img, (y, c) ->
             Color.color(c.getRed(), 0, 0, c.getOpacity())));
        lblStatus.setText("[Red - Applied] (ready)");
    }

    @FXML
    private void redGray(ActionEvent e) {
        lblStatus.setText("[RedGray - Transforming...] (working)");
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

        lblStatus.setText("[RedGray - Applied] (ready)");
    }

    @FXML
    private void openFilter(ActionEvent e) {
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
    }
    @FXML
    private void showAbout(ActionEvent evt) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("aboutWindow.fxml"));
            filterStage = new Stage();
            filterStage.setUserData(this);

            filterStage.setTitle("About Image Manipulator");
            filterStage.setScene(new Scene(root, 751, 187));
            filterStage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    private void performExit(ActionEvent evt) {
        ((Stage) imageView.getScene().getWindow()) .close();
    }

    @FXML
    public void imgMouseOver(MouseEvent evt) {
        if (smplCtl == null) return; // general safeguard

        if (imageView.getImage() == null) return; // image is not loaded yet

        final int x = (int) evt.getX();
        final int y = (int) evt.getY();

        // upscale X & Y b/c 1px of screen does not always equal 1px of image
        // since the images get resized to fit within imagebox.
        // (ex. a 4k photo is not taking up two 1080 monitors)

        // these upscaled values are estimated, so if they are out of bounds they will just be lowered

        int upscaledX = (int) (imageView.getImage().getWidth() / imageView.getFitWidth() * x);
        int upscaledY = (int) (imageView.getImage().getHeight() / imageView.getFitHeight() * y);

        while (upscaledX >= imageView.getImage().getWidth()) { upscaledX--; }
        while (upscaledY >= imageView.getImage().getHeight()) { upscaledY--; }

        lblStatus.setText("[Sampled Color: " + x + ", " + y + "] (ready)");

        Color color = imageView.getImage().getPixelReader().getColor(upscaledX, upscaledY);
        smplCtl.setColor(color);
    }

    @FXML
    private void launchMouseOverWindow(ActionEvent evt) {
        try {
            Parent swatchRoot;
            Stage swatchStage;
            FXMLLoader swatchLoader;

            swatchLoader = new FXMLLoader();
            swatchLoader.setLocation(getClass().getResource("sampleColorWindow.fxml"));
            swatchStage = new Stage();
            smplCtl = new SampleColorWindowController();
            swatchLoader.setController(smplCtl);

            swatchRoot = swatchLoader.load();

            swatchStage.setTitle("Color Observer");
            swatchStage.setScene(new Scene(swatchRoot, 180, 155));
            swatchStage.show();
        } catch (IOException e) {
            Alerting.SimpleError("Generic IO error: " + e.getMessage());
        }
    }

    // endregion

    public void applyKernel(double[] kernel) {
        lblStatus.setText("[Custom Filter - Convolving...] (working)");
        Image img = imageView.getImage();
        imageView.setImage(ImageUtil.convolve(img, kernel));
        lblStatus.setText("[Applied Custom Filter] (ready)");
    }
}
