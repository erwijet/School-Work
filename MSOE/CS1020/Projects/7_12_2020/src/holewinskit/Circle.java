/**
 * Course: CS 1021
 */

package holewinskit;

/**
 * A Circle, containing a radius, color, and (x, y) positions
 */
public class Circle implements Shape {

    private String color;
    private double radius;
    private double posX;
    private double posY;

    /**
     * Creates a new instance of the Circle class
     *
     * @param color A string representing the color of the shape
     * @param radius The radius of the Circle
     * @param posX the X position of the Circle
     * @param posY the Y position of the Circle
     */
    public Circle(String color, double radius, double posX, double posY) {
        this.color = color;
        this.radius = radius;
        this.posX = posX;
        this.posY = posY;


    }

    public double getRadius() {
        return radius;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    /* Implement Shape */

    /**
     * Calculate the area of the Circle
     * @return The area of the Circle
     */
    public double area() {
        return PI * Math.pow(radius, 2);
    }

    /**
     * Calculate the perimeter of the circle
     * @return The perimeter
     */
    public double perimeter() {
        return 2.0 * PI * radius;
    }

    public String getColor() {
        return color;
    }
}
