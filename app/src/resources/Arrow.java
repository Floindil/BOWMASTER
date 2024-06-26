package app.src.resources;

import java.awt.Point;

import app.src.StaticValues;
import app.src.Utilities;
import app.src.StaticValues.Corners;

public class Arrow extends Entity {

    private Point mouseLocation, playerLocation;
    private Boolean shot;
    private double direction;
    private Corners head;
    
    public Arrow(int angle, int x, int y) {
        super("arrow.png", x, y, 10);
        setTAG("arrow");
        mouseLocation = new Point(0,0);
        playerLocation = new Point(0,0);
        shot = false;
        direction = angle;

        setLocation(rect.getX(), rect.getY());
        setDistance(StaticValues.MAX_DISTANCE);
        setSpeed(-20);
    }

    public void setShot() {
        shot = true;
    }

    public Boolean getShot() {
        return shot;
    }

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

    public void updateMouseLocation(int x, int y) {
        mouseLocation = new Point(x, y);
    }

    public void updatePlayerLocation(int x, int y) {
        playerLocation = new Point(x, y);
    }
}
