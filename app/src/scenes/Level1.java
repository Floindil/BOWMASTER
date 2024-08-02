package app.src.scenes;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Clip;

import app.src.StaticValues;
import app.src.resources.Arrow;
import app.src.resources.Bow;
import app.src.resources.Corosshair;
import app.src.resources.Entity;
import app.src.resources.assets.Loader;
import app.src.resources.assets.images.ImageMapping;
import app.src.resources.assets.sounds.SoundMapping;
import app.src.resources.components.Button;
import app.src.resources.monsters.Monster;
import app.src.resources.monsters.MonsterValues;
import app.src.resources.monsters.Wave;
import app.src.resources.monsters.WaveSpawner;

/**
 * Creates the level1 screen.
 * Extends the Scene class.
 * @see Bow
 * @see Scene
 */
public class Level1 extends Scene {
    private Bow bow;
    private Arrow nextArrow;
    private WaveSpawner spawner;
    private int monsterLimit = 5;
    private List<Wave> waves;
    private BufferedImage imgArrow;
    private Clip shotSE;
    
    /**
     * Sets up the Level1 scene.
     * @see Monster
     * @see Bow
     */
    public Level1() {
        super(false);
        setTAG("level");

        BufferedImage imgBow = Loader.loadImage(ImageMapping.BOW);
        imgArrow = Loader.loadImage(ImageMapping.ARROW);
        shotSE = Loader.loadSound(SoundMapping.SHOT);

        MonsterValues GOBCLOPS = new MonsterValues().getGobclops();
        MonsterValues TENTATHULU = new MonsterValues().getTentathulu();
        MonsterValues FLOAKET = new MonsterValues().getFloaket();
        MonsterValues NIGHTLOATER = new MonsterValues().getNighloater();
        MonsterValues THOAT = new MonsterValues().getThoat();

        setBG(ImageMapping.MAP1);
        setBGM(SoundMapping.LEVEL1BGM);

        Wave wave1 = new Wave();
        wave1.registerMonsters(GOBCLOPS, 1);
        wave1.registerMonsters(TENTATHULU, 1);

        Wave wave2 = new Wave();
        wave2.registerMonsters(FLOAKET, 1);
        wave2.registerMonsters(NIGHTLOATER, 1);

        Wave wave3 = new Wave();
        wave3.registerMonsters(THOAT, 1);

        waves = new ArrayList<>();
        waves.add(wave1);
        waves.add(wave2);
        waves.add(wave3);

        spawner = new WaveSpawner(waves, 25, 50);

        Button shooter = new Button(
            StaticValues.CANVAS_WIDTH,
            StaticValues.CANVAS_HEIGHT,
            StaticValues.CANVAS_WIDTH/2,
            StaticValues.CANVAS_HEIGHT/2
        );
        shooter.setAction(() -> {shoot();});
        registerButton(shooter);

        bow = new Bow(imgBow);
        registerEntity(bow);
        Point bowLocation = bow.getLocation();

        nextArrow = new Arrow(imgArrow, bowLocation.x, bowLocation.y);
        registerEntity(nextArrow);
    }

    @Override
    public void update(Point playerLocation) {
        super.update(playerLocation);
        List<Entity> monsters = getEntitiesByTag("monster");
        if (spawner.emptyCheck() && monsters.size() <= 0) {
            Scene menuScene = new Menu();
            setNewScene(menuScene);
        }
        else if (spawner.waveCheck()) {
            if (monsters.size() <= 0) {
                spawner.nextWave();
            }
        }
        else if (spawner.spawnCheck() && monsterLimit > monsters.size()) {
            Monster newMonster = spawner.spawnMonster();
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
        nextArrow.setPlayerLocation(playerLocation.x, playerLocation.y);
    }

    /**
     * Sets the State of the prepared Arrow to shot and creates a new Arrow
     * If the Bow is on cooldown, nothing happens.
     */
    public void shoot() {
        if (bow.getCooldown() <= 0) {
            Corosshair crosshair = getCrosshair();
            Point crosshairLocation = crosshair.getLocation();
            nextArrow.setShot();
            nextArrow.setOriginalImage(nextArrow.getImage());
            nextArrow.setTarget(crosshairLocation.x, crosshairLocation.y);
            Point bowLocation = bow.getLocation();
            Arrow newArrow = new Arrow(imgArrow, bowLocation.x, bowLocation.y);
            registerEntity(newArrow);
            nextArrow = newArrow;
            bow.setCooldown(StaticValues.BOWCOOLDOWN);
            resetCrosshairCharge();
            shotSE.stop();
            shotSE.setFramePosition(0);
            shotSE.start();
        }
    }
}
