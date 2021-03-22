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


    public void scoreThresholdCrossed (int score, int previousScore){
        if (score / scoreDifficultyThreshhold > previousScore / scoreDifficultyThreshhold){
            difficultyLevel++;
            addEnemyOnDifficultyIncrease();
            System.out.println("Difficulty is now " + difficultyLevel);
        }
    }

    /*
    gets called when the score crosses a multiple of 10
    spawns a saw at levels 3 and 5
     */
    private void addEnemyOnDifficultyIncrease() {
        if (difficultyLevel % 2 == 1 && difficultyLevel < 6) {
            objectSpawner.spawnEnemy(ObjectTypeId.Saw);
        } else{
            objectSpawner.spawnEnemy(ObjectTypeId.Zombie);
        }
    }
}
