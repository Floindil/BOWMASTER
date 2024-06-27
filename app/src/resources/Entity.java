package app.src.resources;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import app.src.Utilities;
import app.src.StaticValues.Corners;
import app.src.resources.assets.Loader;
import app.src.resources.components.Hitbox;
import app.src.resources.components.Rectangle;

/**
 * Creates a Entity that serves as a basis for Monsters and other actors.
 * @see BufferedImage
 * @see Rectangle
 */
public class Entity {
    private BufferedImage image, originalImage;
    private int health, distance, speed;
    private List<Hitbox> hitBoxes;
    private Boolean state;
    private String TAG;
    /** Rectangle to track size and location of the Entity */
    public Rectangle rect;

    /**
     * Constructor. Takes an iamge name, coordinates and health value to create an Entity.
     * Loads the image from app/src/resources/assets.
     * Takes width and Height from the image to create a Rectangle
     * @param imageName name of the image in app/src/resources/assets
     * @param x         x coodinate for the location
     * @param y         y coodinate for the location
     * @param health    determines how much damage an entity can take before death
     */
    public Entity(String imageName, int x, int y, int health) {
        hitBoxes = new ArrayList<Hitbox>();
        BufferedImage loadedImage = Loader.loadImage(imageName);
        originalImage = loadedImage;
        image = loadedImage;
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        rect = new Rectangle(imageWidth, imageHeight, x, y);
        distance = 0;
        speed = 0;
        state = true;
        setHealth(health);
    }

    public void setState() {
        state = false;
    }

    public Boolean getState() {
        return state;
    }

    public void setTAG(String newTAG) {
        TAG = newTAG;
    }

    public String getTAG() {
        return TAG;
    }

    /**
     * Base Method for extended classes.
     */
    public void update() {
        if (health < 0) {
            death();
        }
    }

    public void rotateImage(double angle) {
        BufferedImage rotatedImage = Utilities.rotateImage(originalImage, angle);
        setImage(rotatedImage);
        rect.setSize(rotatedImage.getWidth(), rotatedImage.getHeight());
        setLocation(rect.getX(), rect.getY());
    }

    public void scaleImage(double factor) {
        BufferedImage newImage = Utilities.scaleImage(originalImage, factor);
        setImage(newImage);

        int newWidth = newImage.getWidth();
        int newHeight = newImage.getHeight();
        rect.setSize(newWidth, newHeight);
    }

    /**
     * Returns the Entity's image.
     * @return Entity's image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Takes an image and stores it.
     * @param newImage new image to be stored
     */
    public void setImage(BufferedImage newImage) {
        image = newImage;
    }

    public void setOriginalImage(BufferedImage newImage) {
        originalImage = newImage;
    }

    /**
     * Creates a Hitbox with size, offsets and damage multiplier.
     * @param width         width of the Hitbox
     * @param height        height of the Hitbox
     * @param offsetX       x offset to Entity location
     * @param offsetY       y offset to Entity location
     * @param multiplier    multiplier for received damage of this Hitbox
     * @return              the created Hitbox object
     */
    public void addHitBox(int width, int height, int offsetX, int offsetY, int multiplier) {
        Hitbox hitBox = new Hitbox(width, height, offsetX, offsetY, multiplier);
        hitBoxes.add(hitBox);
    }

    /**
     * Takes new coordinates and updates the location of all registered hitboxes.
     * @param newX  new x corrdinate
     * @param newY  new y corrdinate
     */
    public void updateHitBoxes(int newX, int newY) {
        List<Hitbox> boxes = getHitBoxes();
        for (Hitbox box: boxes) {
            box.setLocation(newX, newY);
        }
    }

    /**
     * Takes a factor value to scale all Hitboxes.
     * @param factor scaling factor
     */
    public void scaleHitBoxes(double factor) {
        List<Hitbox> boxes = getHitBoxes();
        for (Hitbox box: boxes) {
            Point originalSize = box.getOriginalSize();
            Point newSize = Utilities.scaleSize(originalSize.x, originalSize.y, factor);
            box.setSize(newSize.x, newSize.y);
            
            Point originalOffsets = box.getOriginalOffsets();
            Point newOffsets = Utilities.scaleSize(originalOffsets.x, originalOffsets.y, factor);
            box.setOffsets(newOffsets.x, newOffsets.y);
        }
    }

    /**
     * Returns a list of all Hitboxes.
     * @return all Hitboxes
     */
    public List<Hitbox> getHitBoxes() {
        return hitBoxes;
    }

    /**
     * Takes x and y coordinates to set a new location for the Entity.
     * @param newX  new x coordinate for the Entity
     * @param newY  new y coordinate for the Entity
     */
    public void setLocation(int newX, int newY) {
        rect.setLocation(newX, newY);
    }

    /**
     * Returns the Entity's location.
     * @return Entity's location
     */
    public Point getLocation() {
        return rect.getLocation();
    }

    /**
     * Returns the Entity's drawing location (top left corner).
     * @return Entity's drawing location
     */
    public Point getDrawPosition() {
        return rect.getCorner(Corners.TOP_LEFT);
    }

    /**
     * Returns the Entity's speed.
     * @return Entity's speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Takes a int value and stores it in speed.
     * @param newSpeed new value to be stored
     */
    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

    /**
     * Returns the Entity's distance.
     * @return Entity's distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Takes a int value and stores it in distance.
     * @param value new value to be stored
     */
    public void setDistance(int value) {
        distance = value;
    }

    /**
     * Gets distance and speed of the Entity to calculate the new distance.
     */
    public void updateDistance() {
        int newDistance = getDistance() + getSpeed();
        setDistance(newDistance);
    }

    /**
     * Takes an image and stores it.
     * @param value new image to be stored
     */
    public void setHealth(int value) {
        health = value;
    }

    public int getHealth() {
        return health;
    }

    /**
     * Takes a int value and adds it to the Entity's health.
     * @param value will be added to Entity health
     */
    public void updateHealth(int value) {
        health += value;
        if (health <= 0) {
            death();
        }
    }

    /**
     * Determines what happens when the Entity dies.
     */
    public void death() {
        setState();
    }
}