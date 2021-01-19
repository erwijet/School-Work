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

public class SimpleIngredient implements Ingredient {
    private double calories;
    private double cups;
    private boolean isDry;
    private String name;

    /**
     * A simple, base ingrediant to be modified and cooked
     * @param calories The amount of energy in kcal (Cal)
     * @param cups The volume in Cups
     * @param isDry True if the ingrediant is dry, false if it is wet
     * @param name The name of the ingrediant
     */
    public SimpleIngredient(double calories, double cups, boolean isDry, String name) {
        this.calories = calories;
        this.cups = cups;
        this.isDry = isDry;
        this.name = name;
    }

    /**
     * Gets the number of Calories in the ingredient
     *
     * @return The number of calories in kCal
     */
    public double getCalories() {
        return calories;
    }

    /**
     * Gets the volume of the ingredient
     *
     * @return The volume in Cups
     */
    public double getCups() {
        return cups;
    }

    /**
     * Gets the name of the Ingredient
     *
     * @return The name of the ingredient
     */
    public String getName() {
        return name;
    }

    /**
     * Determines if the ingreident is considered a "dry" ingreident or not
     *
     * @return true if the ingreidient is dry. False if it is wet
     */
    public boolean isDry() {
        return isDry;
    }

    /**
     * Print the preparation instructions for this ingredient
     */
    public void printRecipe() {
        out.println(DIVIDER);
        out.println(getName());
        out.println(DIVIDER);
        out.println("Cups: " + CUP_FORMAT.format(getCups()) + " Cups");
        out.println("Energy: " + Math.round(getCalories()) + " Calories");
        out.println();
    }
}
