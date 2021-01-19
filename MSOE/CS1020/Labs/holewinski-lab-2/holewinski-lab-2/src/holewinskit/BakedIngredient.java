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

public class BakedIngredient implements Ingredient {
    private Ingredient bakedIngredient;
    private double expansionFactor;

    /**
     * Bake an existing ingrediant
     * @param ingredient The existing ingrediant
     * @param expansionFactor the factor by which the volume of the ingredient
     */
    public BakedIngredient(Ingredient ingredient, double expansionFactor) {
        this.bakedIngredient = ingredient;
        this.expansionFactor = expansionFactor;
    }

    /**
     * Gets the number of Calories in the ingredient
     *
     * @return The number of calories in kCal
     */
    public double getCalories() {
        return bakedIngredient.getCalories();
    }

    /**
     * Gets the volume of the ingredient
     *
     * @return The volume in Cups
     */
    public double getCups() {
        return bakedIngredient.getCalories() * expansionFactor;
    }

    /**
     * Gets the name of the Ingredient
     *
     * @return The name of the ingredient
     */
    public String getName() {
        return "Baked " + bakedIngredient.getName();
    }

    /**
     * Determines if the ingreident is considered a "dry" ingreident or not
     *
     * @return true if the ingreidient is dry. False if it is wet
     */
    public boolean isDry() {
        return bakedIngredient.isDry();
    }

    /**
     * Print the preparation instructions for this ingredient
     */
    public void printRecipe() {
        out.println(DIVIDER);
        out.println(getName());
        out.println(DIVIDER);
        out.println("Ingredient to be baked: " + bakedIngredient.getName());
        out.println("Cups: " + CUP_FORMAT.format(getCups()) + " Cups");
        out.println("Energy: " + Math.round(getCalories()) + " Calories");
        out.println();
        bakedIngredient.printRecipe();
    }
}
