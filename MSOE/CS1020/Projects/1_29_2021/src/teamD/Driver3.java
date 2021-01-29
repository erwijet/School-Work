/**
 * Course: CS1021 - Berisha
 * Winter 2021
 * <1_29_2021>
 * Name: Tyler Holewinski
 * Created: 01/29/2021
 */

package teamD;

import java.awt.print.Printable;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;

public class Driver3 {
    public static void main(String[] args) {
        Complex c1 = new Complex(1.0, 0.0);
        Complex c2 = new Complex(-2.3, -5.0);
        Complex c3 = new Complex(0.0, 3.0);
        Complex c4 = new Complex(8.0, 8.0);

        try(PrintWriter pw = new PrintWriter(new File("list.txt"))){
            c1.write(pw);
            c2.write(pw);
            c3.write(pw);
            c4.write(pw);
        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }

    }
}
