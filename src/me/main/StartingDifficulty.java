package me.main;

public enum StartingDifficulty {
    EASY(1),
    MEDIUM(3),
    HARD(5);
    public int difficultyLevel;

    StartingDifficulty(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
