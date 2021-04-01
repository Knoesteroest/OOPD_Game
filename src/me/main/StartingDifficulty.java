package me.main;

/**
 * Class for Starting Difficulty
 * The starting difficulty gives a value based on the users input. this way the payer can start the game on other levels the one is more challenging than the others
 */
public enum StartingDifficulty {

    EASY(1),
    //TODO starting difficultyLevel variable
    MEDIUM(3),
    HARD(5);

    private int difficultyLevel;

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    /**
     * Sets the hardness of the beginning of the game
     * @param difficultyLevel how hard the level is from the beginning of the game
     */
    StartingDifficulty(int difficultyLevel) {

        this.difficultyLevel = difficultyLevel;
    }
}
