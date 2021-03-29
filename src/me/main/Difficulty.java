package me.main;

public class Difficulty {

    private int difficultyLevel;
    private final int scoreDifficultyThreshhold = 2; //10
    private ObjectSpawner objectSpawner;


    public Difficulty(ObjectSpawner objectSpawner, int difficultyLevel)
    {
        this.objectSpawner = objectSpawner;
        this.difficultyLevel = difficultyLevel;
    }

    /*
    gets called when Player score increases
    checks if the difficulty needs to go up
     */
    public void scoreThresholdCrossed (int score, int previousScore){
        if (score / scoreDifficultyThreshhold > previousScore / scoreDifficultyThreshhold){
            difficultyLevel++;
            addEnemy();
            System.out.println("Difficulty is now " + difficultyLevel);
        }
    }

    /*
    gets called when the score crosses a multiple of 10
    spawns a saw at levels 3 and 5 at tile 1 or 2
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
