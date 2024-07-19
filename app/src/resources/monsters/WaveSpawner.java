package app.src.resources.monsters;

import java.util.List;

/**
 * Extends the MonsterSpawner to create Monsters from a List
 * and allows to limit the amount of a Monster TYPE.
 * @see MonsterSpawner
 * @see Monster
 * @see Wave
 */
public class WaveSpawner extends MonsterSpawner {

    private List<Wave> waves;
    private Wave activeWave;
    private int activeWaveIndex;
    private boolean empty;

    /**
     * Takes a list of Waves, a lower and upper Spawn time limit to create a WaveSpawner
     * @param newWaves Waves to be spawned
     * @param lowerSpawnTime lower Spawn time limit
     * @param upperSpawnTime upper Spawn time limit
     */
    public WaveSpawner(List<Wave> newWaves ,int lowerSpawnTime, int upperSpawnTime) {
        super(lowerSpawnTime, upperSpawnTime);
        waves = newWaves;
        activeWaveIndex = 0;
        activeWave = waves.get(activeWaveIndex);
        updateMonsterPool(activeWave.getMonsters());
    }

    @Override
    public Monster spawnMonster() {
        Monster monster = super.spawnMonster();
        activeWave.decreaseRemaining(monster.getTYPE());
        return monster;
    }

    /**
     * Checks, if there are any Waves left.
     * @return true, if no more Waves are left
     */
    public boolean emptyCheck() {
        if (empty) {
            return empty;
        }
        else if (activeWave.emtpyCheck() && activeWaveIndex + 1 >= waves.size()) {
            System.out.println("SPAWNER EMPTY!");
            empty = true;
            return empty;
        }
        else {
            return empty;
        }
    }

    /**
     * Checks, if the current Wave is empty.
     * @return true, if the current Wave is empty
     */
    public boolean waveCheck() {
        return activeWave.emtpyCheck();
    }

    /**
     * Uses the activeWaveIndex to set a new Wave.
     */
    public void nextWave() {
        activeWaveIndex += 1;
        activeWave = waves.get(activeWaveIndex);
        updateMonsterPool(activeWave.getMonsters());
        System.out.println("> next wave deployed");
    }
}
