package app.src.resources.monsters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.src.StaticValues;

/**
 * Contains utilities to Spawn new Monsters
 */
public class MonsterSpawner {
    private List<MonsterValues> monsterPool;
    private Random duck;
    private Point SpawnRangeX, SpawnTimeRange;
    private int cooldownCounter, monsterCounter;

    /**
     * Takes a lower and upper limit for the delay before the next Monster is spawned
     * to create a MonsterSpawner
     * @param lowerSpawnTime lower limit for the delay before the next Monster is spawned
     * @param upperSpawnTime upper limit for the delay before the next Monster is spawned
     */
    public MonsterSpawner(int lowerSpawnTime, int upperSpawnTime) {
        duck = new Random();
        monsterPool = new ArrayList<>();
        int padding = StaticValues.SPAWNPADDING;
        SpawnRangeX = new Point(0 + padding, StaticValues.CANVAS_WIDTH - padding);
        SpawnTimeRange = new Point(lowerSpawnTime, upperSpawnTime);
        cooldownCounter = 0;
    }

    /**
     * Returns the number of already spawned Monsters.
     * @return Monster count
     */
    public int getMonsterCount() {
        return monsterCounter;
    }

    /**
     * Removes all monsters from the Monster pool.
     * @param monsters New List of Monsters
     */
    public void updateMonsterPool(List<MonsterValues> monsters) {
        monsterPool = monsters;
    }

    /**
     * Checks, if the Spawner is on cooldown.
     * Sets the cooldown to max, if its lower than 0.
     * @return false, when the Spawner is on cooldown
     */
    public Boolean spawnCheck() {
        if (cooldownCounter > 0) {
            cooldownCounter -= 1;
            return false;
        }
        else {
            setCooldown();
            return true;
        }
    }

    /**
     * Adds a Moster type to the Spawn pool.
     * @param monster MonsterValues
     */
    public void registerMonster(MonsterValues monster) {
        MonsterValues values = new MonsterValues(
            monster.getImage(),
            monster.getSpeed(),
            monster.getHealth(),
            monster.getHitboxes()
        );
        monsterPool.add(values);
    }

    /**
     * Takes a Monster type by random from the pool,
     * Creates the Monster and returns it.
     * @return random Monster from the pool
     */
    public Monster spawnMonster() {
        int index = duck.nextInt(monsterPool.size());
        MonsterValues mv = monsterPool.get(index);
        Monster nextMonster = new Monster(mv.getImage(), mv.getHealth(), mv.getSpeed(), mv.getTYPE());
        for (int[] value: mv.getHitboxes()) {
            nextMonster.addHitBox(value[0], value[1], value[2], value[3], value[4]);
        }
        int newX = duck.nextInt(SpawnRangeX.y - SpawnRangeX.x) + SpawnRangeX.x;
        nextMonster.setX(newX);
        System.out.println(
            "Spawned a " +
            nextMonster.getTYPE() +
            " at location " +
            nextMonster.getLocation().x +
            ", " +
            nextMonster.getLocation().y);
        monsterCounter += 1;
        return nextMonster;
    }

    /**
     * Uses upper and lower time range to set the cooldown
     * of the Spawner to a random time iside the range.
     */
    private void setCooldown() {
        int max = SpawnTimeRange.y;
        int min = SpawnTimeRange.x;
        cooldownCounter = duck.nextInt(max - min) + min;
    }
}
