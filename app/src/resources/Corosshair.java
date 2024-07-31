package app.src.resources;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

import app.src.StaticValues;
import app.src.Utilities;
import app.src.resources.assets.Loader;
import app.src.resources.assets.sounds.SoundMapping;

/**
 * Provides functionalities to create and handle a crosshair on the screen 
 * Currently also holds attack charging functionalties,
 * which might be moved to Arrow or Bow later on.
 */
public class Corosshair extends Entity {
    
    private int range, charge, overcharge, chargeCooldown;
    private int chargeLimit = StaticValues.CHARGELIMIT;
    private int overchargeLimit = StaticValues.OVERCHARGELIMIT;
    private int chargeCooldownLimit = StaticValues.CHARGECOOLDOWN;
    private Point mouseLocation;
    private Clip drawNoise;

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
        setTAG("crosshair");
        mouseLocation = new Point();
        range = 200;
        drawNoise = Loader.loadSound(SoundMapping.DRAW);
    }

    @Override
    public void update() {
        super.update();
        updateCharge();
        Point playerLocation = getPlayerLocation();
        double angle = Utilities.calcAngle(playerLocation, mouseLocation);
        int chargedRange = range + charge * 20;
        int x = playerLocation.x + (int) (Math.sin(angle) * chargedRange);
        int y = playerLocation.y - (int) (Math.cos(angle) * chargedRange);
        setLocation(x, y);
    }

    /**
     * Increased the charged variable while charging is true.
     */
    private void updateCharge() {
        if (chargeCooldown == 0) {
            if (getCharging()) {
                if (overcharge >= overchargeLimit) {
                    overcharge = 0;
                    resetCharge();
                }
                ++charge;
                if (charge == 1) {
                    drawNoise.start();
                }
                if (charge >= chargeLimit) {
                    charge = chargeLimit;
                    ++overcharge;
                }
            }
            else {
                resetCharge();
            }
        }
        else {
            --chargeCooldown;
        }
        
    }

    /**
     * Sets the value of the charge varible to 0.
     */
    public void resetCharge() {
        charge = 0;
        chargeCooldown = chargeCooldownLimit;
        drawNoise.stop();
        drawNoise.setFramePosition(0);
    }

    /**
     * Returns the value of the charge variable.
     * @return value of the charge variable
     */
    public int getCharge() {
        return charge;
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
