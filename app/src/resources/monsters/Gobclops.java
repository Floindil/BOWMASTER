package app.src.resources.monsters;

/**
 * Extends the Monster class to a Gobclops.
 * Defines Health, Speed and Hitboxes for all Gobclopses.
 */
public class Gobclops extends Monster {
    
    /** Creates a Gobclops! */
    public Gobclops() {
        super("gobclops.png", 100, 1);
        addHitBox(130, 170, 0, 0, 1);
        addHitBox(45, 42, 0, -37, 2);
        addHitBox(80, 60, 0, 40, 2);
    }
}
