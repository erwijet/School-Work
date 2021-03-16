/**
 * Course: CS1021 - Berisha
 * Winter 2021
 * <final_exam_scratch>
 * Name: Tyler Holewinski
 * Created: 02/25/2021
 */

public class Apple extends Fruit {
    private int numSeeds;

    public Apple(String type, double volume, int numSeeds) throws IllegalArgumentException {
        super(type, volume);

        if (numSeeds < 0)
            throw new IllegalArgumentException("Negative seeds for " + type);
        if (volume < 0)
            throw new IllegalArgumentException("Negative volume for " + type);
        this.numSeeds = numSeeds;
    }

    public void removeSeeds() {
        this.numSeeds = 0;
    }

    public String toString() {
        return "Apple[" + getType() + "," + getVolume() + "," + numSeeds;
    }

    @Override
    public boolean isPrepped() {
        return numSeeds == 0;
    }
}
