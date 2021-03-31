package HUD;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.TextObject;
import processing.core.PGraphics;

public class scoreBoard extends GameObject {
    protected int score;
    private TextObject totalScore;

    @Override
    public void update() {}

    @Override
    public void draw(PGraphics g) {}

    public void setScore(int score) {
        this.score += score;
        System.out.println("SET SCORE" + score);
        System.out.println("Score ADDED");
    }

    public int getScore() {
        return score;
    }
}
