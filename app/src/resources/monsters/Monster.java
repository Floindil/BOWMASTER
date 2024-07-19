package app.src.resources.monsters;

import java.awt.Point;
import java.awt.image.BufferedImage;

import app.src.StaticValues;
import app.src.Utilities;
import app.src.resources.Entity;

/**
 * Creates and handles Monster objects.
 * Extends the Entity class.
 * @see Entity
 */
public class Monster extends Entity {

    private String TYPE;

    /**
     * Creates a Monster object.
     * @param loadedImage previously loaded Image for the Monster
     * @param health hitpoints the Monster can take before dying
     * @param speed speed of the Monster
     * @param type TYPE of the Monster
     */
    public Monster(BufferedImage loadedImage, int health, int speed, String type) {
        super(loadedImage, StaticValues.CANVAS_WIDTH/2, StaticValues.SpawnY, health);
        setTAG("monster");
        setSpeed(speed);
        TYPE = type;
    }

    /**
     * Returns the TYPE of the Monster.
     * @return TYPE
     */
    public String getTYPE() {
        return TYPE;
    }

    /**
     * Sets the x coordinate to a specified value.
     * @param newX new value for location.x
     */
    public void setX(int newX) {
        Point location = getLocation();
        setLocation(newX, location.y);
    }

    /**
     * Updates and scales the position and Hitboxes of the Monster object based on the Distance value.
     */
    @Override
    public void update() {
        super.update();
        if (getState()) {
            int dist = getDistance();
            if (dist + getSpeed() >= StaticValues.MAX_DISTANCE) {
                dist = StaticValues.MAX_DISTANCE;
            }
            else {
                updateDistance();
            }

            double factor = (double) getDistance()  / (double) StaticValues.MAX_DISTANCE;

            scaleImage(factor);
            scaleHitBoxes(factor);

            int newY = StaticValues.SpawnY + (int) (StaticValues.TRAVEL_DISTANCE_Y*factor);
            Point pos = rect.getLocation();

            Point playerLocation = getPlayerLocation();
            double angle = Utilities.calcAngle(playerLocation, pos);
            int deltaX = Math.abs((int) (Math.tan(angle) * getSpeed()));
            int newX = pos.x + deltaX;
            if (pos.x > playerLocation.x) {
                newX = pos.x - deltaX;
            }
            pos.x = newX;

            pos.y = newY;
            setLocation(pos.x, pos.y);
            updateHitBoxes(pos.x, pos.y);
        }
    }
}