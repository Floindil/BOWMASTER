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

    private Point mouseLocation, target;
    private int targetDistance;
    /** Indicates, if the Arrow has been shot. */
    private Boolean shot;
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
        shot = false;
        target = new Point();

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
     * Takes coordinates to set as the target values
     * @param x target x coordinate
     * @param y target y coordinate
     */
    public void setTarget(int x, int y) {
        target = new Point(x, y);
        targetDistance = Utilities.calcDistance(getPlayerLocation(), target);
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
            distanceCheck();

            double factor = (double) getDistance()  / (double) StaticValues.MAX_DISTANCE;
            scaleImage(factor);
            updateLocation(factor);
            int polarDistance = Utilities.calcDistance(getPlayerLocation(), getLocation());
            if (polarDistance > targetDistance) {
                setState();
            }
        }
        else {
            double angle = Utilities.calcAngle(getLocation(), mouseLocation);
            if (angle < 0) {
                head = Corners.TOP_LEFT;
            } else {
                head = Corners.TOP_RIGHT;
            }
            rotateImage(angle);
        }
    }

    /**
     * Initiate Death of Entity, if the distance would be reduced to 0.
     */
    private void distanceCheck() {
        int dist = getDistance();
        if (dist + getSpeed() <= 0) {
            setState();
        }
        else {
            updateDistance();
        }
    }

    /**
     * Updates the y value based on factor and the total travel Distance.
     * Updates the x value based on the angle to the target and the speed.
     * @param factor describes the distance in relation to the total travel Distance
     */
    private void updateLocation(double factor) {
        int newY = StaticValues.SpawnY + (int) (StaticValues.TRAVEL_DISTANCE_Y*factor);
        Point location = getLocation();
        double angle = Utilities.calcAngle(location, target);
        int newX = location.x + (int) (Math.abs(getSpeed()) * Math.tan(angle));
        setLocation(newX, newY);
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
}
