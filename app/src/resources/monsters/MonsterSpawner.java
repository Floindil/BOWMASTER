package app.src.resources.monsters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.src.StaticValues;

public class MonsterSpawner {
    private List<Monster> availableMonsters;
    private Random duck;
    private Point SpawnRangeX, SpawnTimeRange;
    private int cooldownCounter;

    public MonsterSpawner(int lowerSpawnTime, int upperSpawnTime) {
        availableMonsters = new ArrayList<>();
        int padding = StaticValues.SPAWNPADDING;
        SpawnRangeX = new Point(0 + padding, StaticValues.CANVAS_WIDTH - padding);
        SpawnTimeRange = new Point(lowerSpawnTime, upperSpawnTime);
        cooldownCounter = 0;
    }

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

    public void registerMonster(Monster monster) {
        availableMonsters.add(monster);
    }

    public Monster spawnMonster() {
        int index = duck.nextInt(availableMonsters.size());
        Monster nextMonster = availableMonsters.get(index);
        int newX = duck.nextInt(SpawnRangeX.y - SpawnRangeX.x) + SpawnRangeX.x;
        nextMonster.setX(newX);
        return nextMonster;
    }
    private void setCooldown() {
        int max = SpawnTimeRange.y;
        int min = SpawnTimeRange.x;
        cooldownCounter = duck.nextInt(max - min) + min;
    }
}
