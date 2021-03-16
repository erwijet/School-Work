/**
 * Course: CS1021 - Berisha
 * Winter 2021
 * <final_exam_scratch>
 * Name: Tyler Holewinski
 * Created: 02/25/2021
 */

public class FoodOMatic {
    public FoodOMatic() { }

    public void Prep(Fruit fruit) {
        String msg = "Prepping " + fruit.getType() + "... ";
        if (fruit.isPrepped()) {
            msg += "skipping, already prepped.";
        } else if (fruit instanceof Apple) {
            msg += "removing seeds.";
            ((Apple) fruit).removeSeeds();
        } else if (fruit instanceof Banana) {
            msg += "removing peel.";
        }
        System.out.println(msg);
    }

    public double blend(Fruit fruit) {
        String msg = "Juicing " + fruit.getType();
        double juice = 0;
        if (fruit.isPrepped()) {
            msg += " extracted " + fruit.getVolume() + " units of juice.";
            juice = fruit.getVolume();
        } else {
            msg += " not prepped, skipping juice.";
        }

        return juice;
    }
}
