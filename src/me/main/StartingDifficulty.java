package me.main;

public enum StartingDifficulty {

    EASY(1),
    MEDIUM(3),
    HARD(5);

    private int difficultyLevel;

    public int getDifficultyLevel() {
        return difficultyLevel;
    }



    StartingDifficulty(int difficultyLevel) {

        this.difficultyLevel = difficultyLevel;
    }
}
