/**
 * Course: CS1021 - Berisha
 * Winter 2021
 * <1_29_2021>
 * Name: Tyler Holewinski
 * Created: 01/29/2021
 */

package teamD;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver2 {

    //Write another driver program that reads two complex numbers from a
    // binary file and displays them to the console in both cartesian and polar form.

    public static void main(String[] args){
        Complex one;
        Complex two;

        Scanner in = new Scanner(System.in);
        System.out.println("Enter file name");
        String filename = in.nextLine();

        try {
            DataInputStream input = new DataInputStream(new FileInputStream(filename));
            one = Complex.read(input);
            two = Complex.read(input);

            System.out.println("Cartesian:");
            one.setCartesian();
            two.setCartesian();
            System.out.println("First: " + one.toString());
            System.out.println("Second: " + two.toString());
            one.setPolar();
            two.setPolar();
            System.out.println("Polar:");
            System.out.println("First: " + one.toString());
            System.out.println("Second: " + two.toString());


        } catch (FileNotFoundException e){
            System.out.println("Invalid file name");
        }

    }
}
