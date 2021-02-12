/**
 * Course: CS1021 - Berisha
 * Winter 2021
 * <2_8_2021>
 * Name: Tyler Holewinski, Grace Hughes
 * Created: 02/08/2021
 */

package TeamD;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        // Hello All!


    }

    /**
     * @author Tyler Holewinski
     */
    public static void activity4() {
        final List<Complex> nums = Arrays.asList(
                new Complex(8, 8),
                new Complex(5, 0)
            /* etc... */
        );

        // Part 1
        final List<Complex> filtered = Complex.getSpecified(
                nums,
                new IsReal()
        );

        // Part 2
        final List<Complex> filtered2 = Complex.getReal(filtered);
    }

    public static void activity5(List<Complex>nums){
        Complex.getSpecified(nums, c -> c.getReal() > 27);
    }

    public static List<Complex> activity6(List<Complex> complex){
        return complex.stream().filter(x -> x.getMagnitude() > 27).collect(Collectors.toList());
    }

    public static void activity7(List<Complex> complex){
        complex.forEach(x -> System.out.println(x));
    }

    /**
     * @author Tyler Holewinski
     */
    public static Double activity8() {
        final List<Complex> nums = Arrays.asList(
                new Complex(8, 8),
                new Complex(5, 0)
                /* etc... */
        );

        DoubleStream.Builder builder = DoubleStream.builder();

        nums.stream().map(Complex::getAngle).forEach(builder::accept);

        return builder.build().average() .getAsDouble();
    }

    /**
     * @author Grace and Nick
     * */
    public static double activity9() throws IOException {

        return Files.lines(Paths.get("complexies.txt")).map(c -> new Complex(c)).mapToDouble(c -> c.getAngle()).average().getAsDouble();

    }


}
