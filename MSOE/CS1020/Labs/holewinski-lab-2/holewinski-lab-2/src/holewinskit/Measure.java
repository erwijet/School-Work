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

import static java.lang.System.out;

public class Measure implements Ingredient {
    private int numerator;
    private int denominator;
    private Ingredient measuredIngredient;

    /**
     *  Measure an ingrediant
     * @param numerator The numerator
     * @param denominator The denominator
     * @param measuredIngredient the existsing ingrediant
     */
    public Measure(int numerator, int denominator, Ingredient measuredIngredient) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.measuredIngredient = measuredIngredient;
    }

    public Measure(int numerator, Ingredient measuredIngredient) {
        this.numerator = numerator;
        this.denominator = 1;
        this.measuredIngredient = measuredIngredient;
    }

    private String formatQuantity() {
        return numerator + (denominator != 1 ? " / " + denominator : "") +
                (Math.round(numerator / denominator) > 1 ? " Cups" : " Cup") +
                " (" + CUP_FORMAT.format(numerator / denominator) + " Cups)";
    }

    /**
     * Gets the number of Calories in the ingredient
     *
     * @return The number of calories in kCal
     */
    public double getCalories() {
        return measuredIngredient.getCalories() / measuredIngredient.getCups() * numerator / denominator;
    }

    /**
     * Gets the volume of the ingredient
     *
     * @return The volume in Cups
     */
    public double getCups() {
        return numerator / denominator;
    }

    /**
     * Gets the name of the Ingredient
     *
     * @return The name of the ingredient
     */
    public String getName() {
        int roundedCups = (int) Math.round(getCups());
        return  roundedCups + (roundedCups > 1 ? " Cups " : " Cup ") + measuredIngredient.getName();
    }

    /**
     * Determines if the ingredient is considered a "dry" ingredient or not
     *
     * @return true if the ingredient is dry. False if it is wet
     */
    public boolean isDry() {
        return measuredIngredient.isDry();
    }

    /**
     * Print the preparation instructions for this ingredient
     */
    public void printRecipe() {
        out.println(DIVIDER);
        out.println(getName());
        out.println(DIVIDER);
        out.println("Measured ingredient: " + measuredIngredient.getName());
        out.println("Cups: " + formatQuantity());
        out.println("Energy: " + CUP_FORMAT.format(getCalories()) + " Calories");
        out.println();
        measuredIngredient.printRecipe();
    }
}
