/**
 * Course: CS 1021
 * =======================
 * Name: Tyler Holewinski
 * Date: 12/8/2020
 * =======================
 * holewinskit@msoe.edu
 * gh/erwijet
 */


package holewinskit;

import java.text.DecimalFormat;

/**
 * An ingredient that holds a volume, a number of Calories, a Name, and a set of preparation instructions
 */
public interface Ingredient {
    DecimalFormat CUP_FORMAT = new DecimalFormat("#.##");
    String DIVIDER = "====================================================";

    /**
     * Gets the number of Calories in the ingredient
     * @return The number of calories in kCal
     */
    double getCalories();

    /**
     * Gets the volume of the ingredient
     * @return The volume in Cups
     */
    double getCups();

    /**
     * Gets the name of the Ingredient
     * @return The name of the ingredient
     */
    String getName();

    /**
     * Determines if the ingreident is considered a "dry" ingreident or not
     * @return true if the ingreidient is dry. False if it is wet
     */
    boolean isDry();

    /**
     * Print the preparation instructions for this ingredient
     */
    void printRecipe();
}
