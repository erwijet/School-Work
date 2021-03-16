/**
 * Course: CS1021 - Berisha
 * Winter 2021
 * <final_exam_scratch>
 * Name: Tyler Holewinski
 * Created: 02/25/2021
 */

public abstract class Fruit {
    private double volume;
    private String type;

    public Fruit(String type, Double volume) {
        this.type = type;
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }

    public String getType() {
        return type;
    }

    public abstract boolean isPrepped();
}
