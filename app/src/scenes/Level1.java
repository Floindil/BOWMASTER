package app.src.scenes;

import java.awt.Point;
import java.util.List;

import app.src.StaticValues;
import app.src.resources.Arrow;
import app.src.resources.Bow;
import app.src.resources.Entity;
import app.src.resources.components.Button;
import app.src.resources.monsters.Monster;
import app.src.resources.monsters.MonsterSpawner;
import app.src.resources.monsters.MonsterValues;

/**
 * Creates the level1 screen.
 * Extends the Scene class.
 * @see Bow
 * @see Scene
 */
public class Level1 extends Scene {
    private Bow bow;
    private Arrow nextArrow;
    private MonsterSpawner spawner;
    private MonsterValues monsterValues = new MonsterValues();
    private int monsterLimit = 5;
    
    /**
     * Sets up the Level1 scene.
     * @see Monster
     * @see Bow
     */
    public Level1() {
        setTAG("level");

        spawner = new MonsterSpawner(25, 50);
        spawner.registerMonster(monsterValues.getGobclops());

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
    public void update(Point playerLocation) {
        super.update(playerLocation);
        List<Entity> monsters = getEntitiesByTag("monster");
        if (spawner.spawnCheck() && monsterLimit > monsters.size()) {
            Monster newMonster = spawner.spawnMonster();
            newMonster.setPlayerLocation(playerLocation.x, playerLocation.y);
            newMonster.update();
            registerEntity(newMonster);
        };
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
     * Sets the State of the prepared Arrow to shot and creates a new Arrow
     * If the Bow is on cooldown, nothing happens.
     */
    public void shoot() {
        if (bow.getCooldown() <= 0) {
            nextArrow.setShot();
            nextArrow.setOriginalImage(nextArrow.getImage());
            Point bowLocation = bow.getLocation();
            Arrow newArrow = new Arrow(bowLocation.x, bowLocation.y);
            registerEntity(newArrow);
            nextArrow = newArrow;
            bow.setCooldown();
        }
        else {System.out.println(bow.getCooldown());}
    }
}
