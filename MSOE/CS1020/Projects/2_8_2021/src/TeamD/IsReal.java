/**
 * Course: CS1021 - Berisha
 * Winter 2021
 * <2_8_2021>
 * Name: Tyler Holewinski
 * Created: 02/08/2021
 */

package TeamD;

import java.util.function.Predicate;
import java.util.List;
import java.util.stream.Collectors;

public class IsReal implements Predicate<Complex> {

    public boolean test(Complex c){
        return c.getImag() == 0;
    }




}
