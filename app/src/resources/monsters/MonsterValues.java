package app.src.resources.monsters;

public class MonsterValues {

    private String imageName;
    private int speed;
    private int health;
    private int[][] hitboxes;

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

    public void setImageName(String newName) {
        imageName = newName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHitboxes(int[][] hitboxValues) {
        hitboxes = hitboxValues;
    }

    public int[][] getHitboxes() {
        return hitboxes;
    }
}