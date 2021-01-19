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

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.in;
import static java.lang.System.out;

public class Mix implements Ingredient {
    private List<Ingredient> ingredients;
    private String name;

    /**
     * Create a mixture of ingrediant as one ingrediant
     * @param name The name of the new ingrediant
     */
    public Mix(String name) {
        this.name = name;
        ingredients = new ArrayList<Ingredient>();
    }

    /**
     * Add a new ingrediant to the mix
     * @param ingredient The ingrediant to add
     */
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    /**
     * Does the mixture have any dry ingrediants
     * @return True if the mixture has at least one ingrediant that is dry
     */
    public boolean hasDryIngredient() {
        for (Ingredient ingredient :
                ingredients) {
            if (ingredient.isDry()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Does the mixture have any wet ingrediants
     * @return True if the mixture has at least one ingrediant that is dry
     */
    public boolean hasWetIngredient() {
        for (Ingredient ingredient :
                ingredients) {
            if (!ingredient.isDry()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets the number of Calories in the ingredient
     *
     * @return The number of calories in kCal
     */
    public double getCalories() {
        double calories = 0;

        for (Ingredient ingredient :
                ingredients) {
            calories += ingredient.getCalories();
        }

        return calories;
    }

    /**
     * Gets the volume of the ingredient
     *
     * @return The volume in Cups
     */
    public double getCups() {
        double cups = 0;

        for (Ingredient ingredient :
                ingredients) {
            cups += ingredient.getCups();
        }

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
     * Determines if the ingredient is considered a "dry" ingreident or not
     *
     * @return true if the ingredient is dry. False if it is wet
     */
    public boolean isDry() {
        for (Ingredient ingredient :
                ingredients) {
            if (!ingredient.isDry())
                return false;
        }

        return true;
    }

    /**
     * Print the preparation instructions for this ingredient and all its dependents
     */
    public void printRecipe() {
        ArrayList<Ingredient> wets = new ArrayList<Ingredient>();
        ArrayList<Ingredient> drys = new ArrayList<Ingredient>();

        ingredients.forEach(ingredient -> {
                    if (ingredient.isDry()) {
                        drys.add(ingredient);
                    } else {
                        wets.add(ingredient);
                    }
                });

        out.println(DIVIDER);
        out.println(name);
        out.print(DIVIDER);
        if (hasDryIngredient()) {
            out.println("\nDry Ingredients:");
            drys.forEach(i -> out.println("\t" + i.getName()));
        }
        if (hasWetIngredient()) {
            out.println("\nWet Ingredients:");
            wets.forEach(i -> out.println("\t" + i.getName()));
        }
        out.println("\nCups: " + CUP_FORMAT.format(getCups()) + (getCups() > 1 ? " Cups" : " Cup"));
        out.println("Energy: " + Math.round(getCalories()) + " Calories");
        out.println();

        ingredients.forEach(Ingredient::printRecipe);
    }
}
