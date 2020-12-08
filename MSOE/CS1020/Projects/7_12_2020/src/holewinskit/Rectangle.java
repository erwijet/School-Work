/**
 * Course: CS 1021
 */
package holewinskit;

/**
* A Rectangle, defined by a color, width/height, and x/y position
 */

public class Rectangle implements Shape {

    private String color;
    private double width;
    private double height;
    private double xPos;
    private double yPos;

    /**
     * Creates a new Instance of Rectangle class
     *
     * @param color  color of shape (String)
     * @param width  width of shape (double)
     * @param height  height of shape (double)
     * @param xPos  x position of shape (double)
     * @param yPos  y position of shape (double)
     */

    public Rectangle(String color, double width, double height, double xPos, double yPos){
        this.color = color;
        this.width = width;
        this.height = height;
        this.xPos = xPos;
        this.yPos = yPos;

    }

    /**
     * Calculates the area of the shape
     * @return area of the shape
     */
    public double area(){
        return (width * height);
    }

    /**
     * Calculates the perimeter of the shape
     * @return perimeter of the shape
     */
    public double perimeter(){
        return (2.0 * width) + (2.0 * height);
    }

    /**
     * Returns the color of the shape
     * @return color as a string
     */
    public String getColor(){
        return color;
    }

}
