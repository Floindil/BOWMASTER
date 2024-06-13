package app.src.resources.components;

import java.awt.Point;
import java.awt.image.BufferedImage;

import app.src.resources.assets.Loader;

/**
 * Baseclass for a Component
 */
public class Component {

    private BufferedImage image;
    /**Rectangle to track size and location*/
    public Rectangle rect;

    /**
     * Returns the image of the Component
     * @return image of the Component
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Takes an image and stores it.
     * @param newImage  new image to store
     */
    public void setImage(BufferedImage newImage) {
        image = newImage;
    }

    /**
     * Returns the location of the Component
     * @return location of the Component
     */
    public Point getLocation() {
        return rect.getLocation();
    }

    /**
     * Takes coordinates for a new location and stores them 
     * @param newX  new x coordinate for the Component
     * @param newY  new y coordinate for the Component
     */
    public void setLocation(int newX, int newY) {
        Point size = rect.getSize();
        rect.setLocation(newX - size.x/2, newY - size.y/2);
    }

    /**
     * Returns the width of the Component
     * @return width of the Component
     */
    public int getWidth() {
        Point size = rect.getSize();
        return size.x;
    }

    /**
     * Returns the height of the Component
     * @return height of the Component
     */
    public int getHeight() {
        Point size = rect.getSize();
        return size.y;
    }

    /**
     * Takes width and height to store in size.
     * Recalculates the Rectangle.
     * @param newWidth  new width of the Component
     * @param newHeight new height of the Component
     */
    public void setSize(int newWidth, int newHeight) {
        Point location = rect.getLocation();
        rect.setSize(newWidth, newHeight);
        setLocation(location.x, location.y);
    }

    /**
     * Constructor. Creates a Component with size, location and Rectangle
     * @param width     width of the Component
     * @param height    height of the Component
     * @param x         x coordinate of the Component
     * @param y         y coordinate of the Component
     */
    public Component(int width, int height, int x, int y) {
        rect = new Rectangle(width, height, x, y);
    }

    /**
     * Constructor. Creates a Component with image, size, location and Rectangle
     * Loads an image from app/src/resources/assets.
     * @param imageName image name like "image.png"
     * @param x         x coordinate of the Component
     * @param y         y coordinate of the Component
     */
    public Component(String imageName, int x, int y) {
        BufferedImage image = Loader.loadImage(imageName);
        setImage(image);
        int width = image.getWidth();
        int height = image.getHeight();
        rect = new Rectangle(width, height, x, y);
    }
}
