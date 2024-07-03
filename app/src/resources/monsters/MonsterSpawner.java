package app.src.resources.monsters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.src.StaticValues;

public class MonsterSpawner {
    private List<MonsterValues> availableMonsters;
    private Random duck;
    private Point SpawnRangeX, SpawnTimeRange;
    private int cooldownCounter;

    public MonsterSpawner(int lowerSpawnTime, int upperSpawnTime) {
        duck = new Random();
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

    public void registerMonster(MonsterValues monster) {
        availableMonsters.add(monster);
    }

    public Monster spawnMonster() {
        int index = duck.nextInt(availableMonsters.size());
        MonsterValues mv = availableMonsters.get(index);
        Monster nextMonster = new Monster(mv.getImageName(), mv.getHealth(), mv.getSpeed());
        for (int[] value: mv.getHitboxes()) {
            nextMonster.addHitBox(value[0], value[1], value[2], value[3], value[4]);
        }
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
