/**
 * Course: CS1021 - Berisha
 * Winter 2021
 * <1_29_2021>
 * Name: Tyler Holewinski
 * Created: 01/29/2021
 */

package teamD;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Activity 8:
 *
 * Write another driver program that reads all complex numbers in a text file
 * (assume no other data is in the file and that the format described in the
 * previous activity is used) and displays them to the console in cartesian form.
 */
public class Driver4 {
    private static final String FILENAME = "foo.txt"; // edit me

    public static void main(String[] args) {

        try  {
            File file = new File(FILENAME);
            Scanner sc = new Scanner (file);

            String in = sc.nextLine();
            sc.close();

            for (String s : in.split(",")) {
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
