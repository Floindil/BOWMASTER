package app.src.scenes;

import java.awt.Point;

import app.src.StaticValues;
import app.src.resources.Arrow;
import app.src.resources.Bow;
import app.src.resources.components.Button;
import app.src.resources.monsters.Gobclops;
import app.src.resources.monsters.Monster;

/**
 * Creates the level1 screen.
 * Extends the Scene class.
 * @see Bow
 * @see Scene
 */
public class Level1 extends Scene {
    private Bow bow;
    private Arrow nextArrow;
    
    /**
     * Sets up the Level1 scene.
     * @see Monster
     * @see Bow
     */
    public Level1() {
        setTAG("level");
        Gobclops gobclops = new Gobclops();
        registerEntity(gobclops);

        Button shooter = new Button(
            StaticValues.CANVAS_WIDTH,
            StaticValues.CANVAS_HEIGHT,
            StaticValues.CANVAS_WIDTH/2,
            StaticValues.CANVAS_HEIGHT/2
        );
        shooter.setAction(() -> {shoot();});
        registerButton(shooter);

        bow = new Bow();
        registerEntity(bow);
        Point bowLocation = bow.getLocation();

        nextArrow = new Arrow(bowLocation.x, bowLocation.y);
        nextArrow.updatePlayerLocation(bowLocation.x, bowLocation.y);
        nextArrow.updateMouseLocation(0, 0);
        registerEntity(nextArrow);
    }

    @Override
    public void update() {
        super.update();
    }

    /**
     * Includes mouseLocation update for the Bow object.
     * @see Scene
     */
    @Override
    public void updateMouseLocation(int x, int y) {
        super.updateMouseLocation(x, y);
        bow.updateMouseLocation(x, y);
        nextArrow.updateMouseLocation(x, y);
        Point playerLocation = bow.getLocation();
        nextArrow.updatePlayerLocation(playerLocation.x, playerLocation.y);
    }

    /**
     * Sets the State of the prepared Arrow to shot ands creates a new Arrow
     */
    public void shoot() {
        if (bow.getCooldown() <= 0) {
            nextArrow.setShot();
            nextArrow.setOriginalImage(nextArrow.getImage());
            Point bowLocation = bow.getLocation();
            Arrow newArrow = new Arrow(bowLocation.x, bowLocation.y);
            registerEntity(newArrow);
            nextArrow = newArrow;
            bow.setCooldown(10);
        }
        else {System.out.println(bow.getCooldown());}
    }
}
