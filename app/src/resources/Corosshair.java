package app.src.resources;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import app.src.Utilities;

/** Provides functionalities to create and handle a crosshair on the screen */
public class Corosshair extends Entity {
    
    private int range;
    private Point mouseLocation;

    /**
     * Creates a Crosshair Object to aim at Enemys on the Screen.
     */
    public Corosshair() {
        BufferedImage image = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.RED);
        g.setStroke(new BasicStroke(3));
        g.drawLine(10, 0, 10, 20);
        g.drawLine(0, 10, 20, 10);
        Shape theCircle = new Ellipse2D.Double(0, 0, 20, 20);
        g.draw(theCircle);
        super(image, 0, 0, 1);
        mouseLocation = new Point();
        range = 500;
    }

    @Override
    public void update() {
        super.update();
        Point playerLocation = getPlayerLocation();
        double angle = Utilities.calcAngle(playerLocation, mouseLocation);
        int x = playerLocation.x + (int) (Math.sin(angle) * range);
        int y = playerLocation.y - (int) (Math.cos(angle) * range);
        setLocation(x, y);
    }

    /**
     * Set the range of the crosshair,
     * defines the Distance between Player and Crosshair.
     * @param newRange new value for Range
     */
    public void setRange(int newRange) {
        range = newRange;
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
