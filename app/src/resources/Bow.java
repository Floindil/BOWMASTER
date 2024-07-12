package app.src.resources;

import java.awt.Point;

import app.src.StaticValues;
import app.src.Utilities;

/**
 * Extends the Entity class to create a Bow
 * @see Entity
 */
public class Bow extends Entity {
    private Point mousePoint = new Point(0, 0);

    /**
     * Creates a Entity object, specified as Bow
     */
    public Bow() {
        super("Bow1.png", 0, 0, 100);
        int x = StaticValues.PLAYERSPAWNX;
        int y = StaticValues.PLAYERSPAWNY;
        rect.setLocation(x, y);
    }

    /**
     * Rotates the image based on the mouseposition.
     */
    @Override
    public void update() {
        super.update();
        double mouseAngle = Utilities.calcAngle(rect.getLocation(),  mousePoint);
        rotateImage(mouseAngle);
    }

    /**
     * Takes x and y cooridinates to store as mousePosition
     * @param x x coordnate
     * @param y y coordnate
     */
    public void updateMouseLocation(int x, int y) {
        mousePoint = new Point(x, y);
    }
}