/**
 * Course: CS1021 - Berisha
 * Winter 2021
 * <1_29_2021>
 * Name: Tyler Holewinski
 * Created: 01/29/2021
 */

package teamD;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Complex c1 = new Complex(1.0, 0.0);
        Complex c2 = new Complex(-2.3, -5.0);
        Complex c3 = new Complex(0.0, 3.0);
        Complex c4 = new Complex(8.0, 8.0);

        try(DataOutputStream dOut = new DataOutputStream(new FileOutputStream("filename.bin"))){
            c1.write(dOut);
            c2.write(dOut);
            c3.write(dOut);
            c4.write(dOut);
        } catch(IOException e){
            System.out.println("IO exception");
        }

    }
}
