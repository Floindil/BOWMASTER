package app.src.resources.monsters;

/**
 * Stores the blueprints for Monsters.
 */
public class MonsterValues {

    /**
     * Empty Constructor.
     */
    public MonsterValues() {
        //empty
    }

    private String imageName;
    private int speed;
    private int health;
    private int[][] hitboxes;

    /**
     * Creates and returns a Gobclops!
     * @return a Gobclops!
     */
    public MonsterValues getGobclops() {
        setImageName("gobclops.png");
        setSpeed(1);
        setHealth(100);
        final int[] hitbox1 = {130, 170, 0, 0, 1};
        final int[] hitbox2 = {45, 42, 0, -37, 2};
        final int[] hitbox3 = {80, 60, 0, 40, 2};
        final int[][] allHitboxes = {hitbox1, hitbox2, hitbox3};
        setHitboxes(allHitboxes);
        return this;
    }

    /**
     * Sets the name of the Monster image.
     * The name is used to load an image from assets.
     * @param newName new image name
     */
    public void setImageName(String newName) {
        imageName = newName;
    }

    /**
     * Returns the name of the Monster image.
     * @return image name
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Takes a value to store in the speed variable of the Monster.
     * @param newSpeed new value for speed
     */
    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

    /**
     * Returns the speed of the Monster.
     * @return speed value
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Takes a value to store in the health variable of the Monster.
     * @param newHealth new value for health
     */
    public void setHealth(int newHealth) {
        health = newHealth;
    }

    /**
     * Returns the health of the Monster.
     * @return health value
     */
    public int getHealth() {
        return health;
    }

    /**
     * Takes a Matrix of values to create Hitboxes from them later.
     * (width, height, offsetX, offsetY, Damage Multiplier) x Amount of Hitboxes.
     * @param hitboxValues matrix of Hitbox values
     */
    public void setHitboxes(int[][] hitboxValues) {
        hitboxes = hitboxValues;
    }

    /**
     * Returns the matrix of Hitbox values of the Monster.
     * @return Monster Hitboxes
     */
    public int[][] getHitboxes() {
        return hitboxes;
    }
}