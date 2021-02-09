/**
 * Course: CS 1021
 * =======================
 * Name: Tyler Holewinski
 * Date: 2/2/2021
 * =======================
 * holewinskit@msoe.edu
 * gh/erwijet
 */

package holewinskit;

import com.sun.media.sound.InvalidFormatException;
import edu.msoe.cs1021.ImageUtil;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

public class ImageIO {
    private static final List<String> VALID_EXTENSIONS = Arrays.asList("jpg", "png", "msoe");

    private static String getFileExtension(Path path) {
        String[] parts = path.toString().split("\\." /* regex: match "." literal */);
        String ext = "";

        if (parts.length != 1) {
            ext = parts[parts.length - 1];
        }

        return ext;
    }

    public static Image read(Path path) {
        String ext = getFileExtension(path).toLowerCase();
        Image img = null;

        if (VALID_EXTENSIONS.contains(ext)) {
            if (ext.equals("msoe")) {
                img = readMSOE(path);
            } else {
                try {
                    img = ImageUtil.readImage(path);
                } catch (IOException e) {
                    Alerting.SimpleError("Error in loading file! \n" +
                            e.getMessage());
                }
            }
        } else {
            Alerting.SimpleError("Invalid file type. 😵");
        }

        return img;
    }

    private static Image readMSOE(Path path) {
        Image img = null;

        try (Scanner sc = new Scanner(path.toFile())) {
            if (!sc.nextLine().equals("MSOE")) {
                throw new InvalidFormatException("Invalid File Header");
            }

            int width = sc.nextInt();
            int height = sc.nextInt();

            sc.nextLine(); // consume newline
            BufferedImage buff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = buff.createGraphics();


            int x;
            int y = 0;

            while (sc.hasNextLine()) {
                x = 0;
                Scanner thisLineSc = new Scanner(sc.nextLine());
                while (thisLineSc.hasNext()) {
                    javafx.scene.paint.Color c = javafx.scene.paint.Color.web(thisLineSc.next());
                    final float r = (float) c.getRed();
                    final float g = (float) c.getGreen();
                    final float b = (float) c.getBlue();

                    g2d.setColor(new java.awt.Color(r, g, b, 1));
                    g2d.drawRect(x++, y, 1, 1);
                }
                y++;
            }

            g2d.dispose();

            // save image for possible "revert" functionality w/o running expensive O(n^2)
            // algorithm for loading MSOE files with increased efficiency.

            File f = new File("__msoeTemp.jpg");
            javax.imageio.ImageIO.write(buff, "jpg", f);

            img = new Image(new FileInputStream("__msoeTemp.jpg"));

        } catch (FileNotFoundException e) {
            Alerting.SimpleError("Invalid file type. 😵");
        } catch (InvalidFormatException e) {
            Alerting.SimpleError("Corrupted MSOE file format! 😣. " + e.getMessage());
        } catch (InputMismatchException e) {
            Alerting.SimpleError("Corrupted MSOE file format! \n" +
                    "Could not read width / height declarations");
        } catch (IOException e) {
            Alerting.SimpleError("Error when loading MSOE file. \n" +
                    e.getMessage());
        }

        return img;
    }

    private static void writeMSOE(Image img, Path path) {
        File f = path.toFile();

        try (PrintWriter writer = new PrintWriter(f)) {
            writer.write("MSOE\n");
            writer.write("" + (int)img.getWidth() + " " + (int)img.getHeight() + "\n");

            for (int y = 0; y < img.getHeight(); y++) {
                String thisLine = "";

                for (int x = 0; x < img.getWidth(); x++) {
                    thisLine += (toHexString(img.getPixelReader().getColor(x, y)) + " ");
                }

                writer.write(thisLine + "\n");
            }
        } catch (FileNotFoundException e) {
            Alerting.SimpleError("Could not find part of the path! 😑\nSave aborted");
        }
    }

    private static String format(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }

    public static String toHexString(Color value) {
        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
                .toUpperCase();
    }

    public static void write (Image image, Path path) {
        String ext = getFileExtension(path);

        if (VALID_EXTENSIONS.contains(ext)) {
            if (ext.equals("msoe")) {
                writeMSOE(image, path);
            }
            else {
                try {
                    ImageUtil.writeImage(path, image);
                } catch (IOException e) {
                    Alerting.SimpleError("Error while saving file!\n" + e.getMessage());
                }
            }
        }
    }
}
