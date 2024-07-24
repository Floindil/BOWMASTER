package app.src.resources;

import java.awt.Point;
import java.awt.image.BufferedImage;

import app.src.StaticValues;
import app.src.Utilities;
import app.src.StaticValues.Corners;

/**
 * Extends the Entity class to create Arrows
 */
public class Arrow extends Entity {

    private Point mouseLocation, playerLocation;
    /** Indicates, if the Arrow has been shot. */
    private Boolean shot;
    private double direction;
    private Corners head;
    
    /**
     * Takes and Image, x and y coordinates to create an Arrow object.
     * @param loadedImage previously loaded Image
     * @param x x coordinate
     * @param y y coordinate
     */
    public Arrow(BufferedImage loadedImage, int x, int y) {
        super(loadedImage, x, y, 10);
        setTAG("arrow");
        mouseLocation = new Point(0,0);
        playerLocation = new Point(0,0);
        shot = false;
        direction = 0;

        setLocation(rect.getX(), rect.getY());
        setDistance(StaticValues.MAX_DISTANCE);
        setSpeed(-20);
    }

    /**
     * Sets the shot attribute to true.
     */
    public void setShot() {
        shot = true;
    }

    /**
     * Returns the value of the shot attribute.
     * @return value of the shot attribute
     */
    public Boolean getShot() {
        return shot;
    }

    /**
     * Returns a Point for the hitcalculation.
     * The head variable is determined by the direction of the Arrow.
     * @return hit calculation Point
     */
    public Point getHead() {
        return rect.getCorner(head);
    }

    @Override
    public void update() {
        if (shot) {
            int dist = getDistance();
            if (dist + getSpeed() <= 0) {
                setState();
            }
            else {
                updateDistance();
            }
            
            double factor = (double) getDistance()  / (double) StaticValues.MAX_DISTANCE;
            scaleImage(factor);
            
            int newY = StaticValues.SpawnY + (int) (StaticValues.TRAVEL_DISTANCE_Y*factor);
            Point pos = rect.getLocation();
            int newX = pos.x + (int) (Math.abs(getSpeed()) * Math.tan(direction));
            setLocation(newX, newY);
        }
        else {
            direction = Utilities.calcAngle(playerLocation, mouseLocation);
            if (direction < 0) {
                head = Corners.TOP_LEFT;
            } else {
                head = Corners.TOP_RIGHT;
            }
            rotateImage(direction);
        }
    }

    /**
     * Takes x and y coordinates to store as the mouse location.
     * Required to calculate the Arrows direction.
     * @param x x coordinate
     * @param y y coordinate
     */
    public void updateMouseLocation(int x, int y) {
        mouseLocation = new Point(x, y);
    }

    /**
     * Takes x and y coordinates to store as the player location.
     * Required to calculate the Arrows direction.
     * @param x x coordinate
     * @param y y coordinate
     */
    public void updatePlayerLocation(int x, int y) {
        playerLocation = new Point(x, y);
    }
}
