package app.src.resources.monsters;

import java.awt.image.BufferedImage;

/**
 * Stores the blueprints for Monsters.
 */
public class MonsterValues {

    private BufferedImage image;
    private String TYPE;
    private int speed;
    private int health;
    private int[][] hitboxes;

    /**
     * Creates empty MonsterValues.
     */
    public MonsterValues() {
        image = null;
        speed = 0;
        health = 0;
        hitboxes = null;
    }

    /**
     * Creates MonsterValues from the parameters.
     * @param image Image previously loaded from assets
     * @param Speed Speed value of the Monster
     * @param Health Health value of the Monster
     * @param Hitboxes Hitboxes of the Monster
     */
    public MonsterValues(BufferedImage loadedImage, int Speed, int Health, int[][] Hitboxes) {
        image = loadedImage;
        speed = Speed;
        health = Health;
        hitboxes = Hitboxes;
    }

    /**
     * Creates and returns a Gobclops!
     * @param loadedImage previously loaded Image
     * @return a Gobclops!
     */
    public MonsterValues getGobclops(BufferedImage loadedImage) {
        setTYPE("Gobclops");
        setImage(loadedImage);
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
     * Creates and returns a Thoat!
     * @param loadedImage previously loaded Image
     * @return a Thoat!
     */
    public MonsterValues getThoat(BufferedImage loadedImage) {
        setTYPE("Thoat");
        setImage(loadedImage);
        setSpeed(2);
        setHealth(50);
        final int[] hitbox1 = {140, 130, 0, -50, 1};
        final int[] hitbox2 = {180, 130, 0, 80, 1};
        final int[] hitbox3 = {40, 90, 0, 15, 2};
        final int[] hitbox4 = {20, 20, -35, -50, 2};
        final int[] hitbox5 = {20, 20, 35, -50, 2};
        final int[][] allHitboxes = {hitbox1, hitbox2, hitbox3, hitbox4, hitbox5};
        setHitboxes(allHitboxes);
        return this;
    }

    /**
     * Creates and returns a Nighloater!
     * @param loadedImage previously loaded Image
     * @return a Nighloater!
     */
    public MonsterValues getNighloater(BufferedImage loadedImage) {
        setTYPE("Nighloater");
        setImage(loadedImage);
        setSpeed(5);
        setHealth(20);
        final int[] bodyBox = {100, 160, 0, 0, 1};
        final int[] eyeBox = {30, 30, -5, -12, 2};
        final int[][] allHitboxes = {bodyBox, eyeBox};
        setHitboxes(allHitboxes);
        return this;
    }

    /**
     * Creates and returns a Floaket!
     * @param loadedImage previously loaded Image
     * @return a Floaket!
     */
    public MonsterValues getFloaket(BufferedImage loadedImage) {
        setTYPE("Floaket");
        setImage(loadedImage);
        setSpeed(3);
        setHealth(30);
        final int[] lowerBodyBox = {220, 120, 0, 100, 1};
        final int[] upperBodyBox = {90, 110, 0, -10, 1};
        final int[] mouthBox = {30, 90, 0, 10, 2};
        final int[][] allHitboxes = {lowerBodyBox,upperBodyBox, mouthBox};
        setHitboxes(allHitboxes);
        return this;
    }

    /**
     * Creates and returns a Tentathulu!
     * @param loadedImage previously loaded Image
     * @return a Tentathulu!
     */
    public MonsterValues getTentathulu(BufferedImage loadedImage) {
        setTYPE("Tentathulu");
        setImage(loadedImage);
        setSpeed(1);
        setHealth(100);
        final int[] lowerBodyBox = {260, 140, 0, 70, 1};
        final int[] lowerWingBoxL = {100, 60, -95, -50, 1};
        final int[] lowerWingBoxR = {100, 60, 95, -50, 1};
        final int[] upperWingBoxL = {40, 60, -55, -105, 1};
        final int[] upperWingBoxR = {40, 60, 55, -105, 1};
        final int[] extendWingBoxL = {40, 40, -90, -100, 1};
        final int[] extendWingBoxR = {40, 40, 90, -100, 1};
        final int[] headBox = {100, 80, 0, -30, 3};
        final int[] eyeBoxL = {30, 30, -30, 0, 3};
        final int[] eyeBoxR = {30, 30, 30, 0, 3};
        final int[][] allHitboxes = {
            lowerBodyBox,
            lowerWingBoxL,
            lowerWingBoxR,
            upperWingBoxL,
            upperWingBoxR,
            extendWingBoxL,
            extendWingBoxR,
            headBox,
            eyeBoxL,
            eyeBoxR
        };
        setHitboxes(allHitboxes);
        return this;
    }
    
    /**
     * Sets the TYPE of the Monster.
     * @param type TYPE of the Monster
     */
    public void setTYPE(String type) {
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
     * Sets the name of the Monster image.
     * The name is used to load an image from assets.
     * @param newName new image name
     */
    public void setImage(BufferedImage newImage) {
        image = newImage;
    }

    /**
     * Returns the Monster image.
     * @return image
     */
    public BufferedImage getImage() {
        return image;
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