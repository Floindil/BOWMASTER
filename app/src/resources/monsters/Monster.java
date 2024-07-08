package app.src.resources.monsters;

import java.awt.Point;

import app.src.StaticValues;
import app.src.resources.Entity;

/**
 * Creates and handles Monster objects.
 * Extends the Entity class.
 * @see     Entity
 */
public class Monster extends Entity {
    /**
     * Creates a Monster object.
     * @param imageName image for the Monster in app/src/resources/assets
     * @param health    hitpoints the Monster can take before dying
     * @param speed     speed of the Monster
     */
    public Monster(String imageName, int health, int speed) {
        super(imageName, StaticValues.CANVAS_WIDTH/2, StaticValues.SpawnY, health);
        setTAG("monster");
        setSpeed(speed);
        update();
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
            pos.y = newY;
            setLocation(pos.x, pos.y);
            updateHitBoxes(pos.x, pos.y);
        }
    }
}