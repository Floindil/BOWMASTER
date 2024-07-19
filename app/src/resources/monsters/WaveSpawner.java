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
        updateWave();
    }

    @Override
    public Monster spawnMonster() {
        Monster monster = super.spawnMonster();
        activeWave.decreaseRemaining(monster.getTYPE());
        return monster;
    }

    /**
     * Checks, if there are any waves left.
     * @return true, if no more waves are left
     */
    public boolean emptyCheck() {
        if (activeWave.emtpyCheck() && activeWaveIndex + 1 < waves.size()) {
            activeWaveIndex += 1;
            updateWave();
            return false;
        }
        else if (activeWave.emtpyCheck()) {
            System.out.println("SPAWNER EMPTY!");
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Uses the activeWaveIndex to set a new Wave.
     */
    private void updateWave() {
        activeWave = waves.get(activeWaveIndex);
        updateMonsterPool(activeWave.getMonsters());
        System.out.println("update wave");
    }
}
