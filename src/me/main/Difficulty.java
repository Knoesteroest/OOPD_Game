package me.main;

/**
 * This class keeps tracks of difficulty by spawning new enemies as the player's score increases
 * past the threshhold. Player calls this when the score increases and this calls ObjectSpawner
 * to spawn the new enemies.
 */

public class Difficulty {

    private int difficultyLevel;
    private final int scoreDifficultyThreshhold = 2; //10
    private ObjectSpawner objectSpawner;

    public Difficulty(ObjectSpawner objectSpawner, int difficultyLevel)
    {
        this.objectSpawner = objectSpawner;
        this.difficultyLevel = difficultyLevel;
    }

    /**
     Checks if the difficulty needs to go up.
     Gets called when Player score increases.
     */
    public void scoreThresholdCrossed (int score, int previousScore){
        if (score / scoreDifficultyThreshhold > previousScore / scoreDifficultyThreshhold){
            difficultyLevel++;
            addEnemy();
            System.out.println("Difficulty is now " + difficultyLevel);
        }
    }

    /**
    Gets called when the score crosses a multiple of 10.
    Spawns a saw at levels 3 and 5 at tile 1 or 2
    (The first saw spawned at level 1)
     */
    private void addEnemy() {
        if (difficultyLevel % 2 == 1 && difficultyLevel < 6) {
            objectSpawner.spawnSaw(difficultyLevel == 3 ? 1 : 2);
        } else{
            objectSpawner.spawnZombie();
        }
    }
}
