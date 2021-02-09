package holewinskit;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;

public class Lab8Controller {
    // region def instance refs

    @FXML
    private Label lblPath;

    @FXML
    private ImageView imageView;

    @FXML
    public void performOpen(ActionEvent e) {
        FileChooser fc = new FileChooser();
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("JPG Files", "*.jpg"));
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("MSOE Image Files", "*.msoe"));

        Path path = fc.showOpenDialog(Lab8.getPrimaryStage()).toPath();
        lblPath.setText(path.toString());

        imageView.setImage(ImageIO.read(path));
    }

    @FXML
    public void perfromSave(ActionEvent e) {
        FileChooser fc = new FileChooser();
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("JPG Files", "*.jpg"));
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("MSOE Image Files", "*.msoe"));

        Path path = fc.showSaveDialog(Lab8.getPrimaryStage()).toPath();

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

        BufferedImage buff = new BufferedImage((int) img.getWidth(), (int) img.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = buff.createGraphics();

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                javafx.scene.paint.Color fx = img.getPixelReader().getColor(x, y);

                final double r = fx.getRed();
                final double g = fx.getGreen();
                final double b = fx.getBlue();

                final float gray = (float) ((0.2126 * r) + (0.7152 * g) + (0.0722 * b));

                java.awt.Color awtColor = new java.awt.Color(
                        gray,
                        gray,
                        gray
                        , 1);

                g2d.setColor(awtColor);

                g2d.drawRect(x, y, 1, 1);
            }
        }

        g2d.dispose();
        imageView.setImage(SwingFXUtils.toFXImage(buff, null));
    }
    @FXML
    public void performNegative(ActionEvent e) {
        Image img = imageView.getImage();

        BufferedImage buff = new BufferedImage((int) img.getWidth(), (int) img.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = buff.createGraphics();

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                javafx.scene.paint.Color fx = img.getPixelReader().getColor(x, y);

                final double r = 1 - fx.getRed();
                final double g = 1 - fx.getGreen();
                final double b = 1 - fx.getBlue();

                java.awt.Color awtColor = new java.awt.Color(
                        (float)r,
                        (float)g,
                        (float)b
                        , 1);

                g2d.setColor(awtColor);

                g2d.drawRect(x, y, 1, 1);
            }
        }

        g2d.dispose();
        imageView.setImage(SwingFXUtils.toFXImage(buff, null));
    }

    // endregion
}
