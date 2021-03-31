package HUD;

import enemy.Zombie;
import me.main.Game;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.TextObject;
import processing.core.PGraphics;


public class HUD extends Dashboard {
    private HealthBar HB;
    private TextObject displayScore;
    private TextObject displayBooster;
    private int score;
    private Game game;

    public HUD(int width, int height, Game game) {
        super(0, 0, width, 35);
        this.game = game;
        HB = new HealthBar();

        displayScore = new TextObject("Score: 0", 22);
        displayBooster = new TextObject("Booster: ", 22);

        super.addGameObject(HB, 7, 3, 1);
        super.addGameObject(displayScore, 225, 3, 2);
        super.addGameObject(displayBooster, width - 225, 3, 3);
        super.setBackground(255, 255, 255);
    }

    @Override
    public void update() {
        HB.update();
        if (HB.getHEALTH() == 0) {
            deleteAllDashboardObjects();
            game.deleteAllGameOBjects();
            super.height = Game.HEIGHT;
            drawEndScreen();
            game.pauseGame();
        }
    }

    @Override
    public void draw(PGraphics hud) {
        super.draw(hud);
    }

    private void drawEndScreen() {
        TextObject title = new TextObject("Game Over", 50);
        displayScore = new TextObject("Score: " + getScore(), 50);

        super.addGameObject(title, Game.WIDTH / 2 - 140, Game.HEIGHT / 4, 1);
        super.addGameObject(displayScore, Game.WIDTH / 2 - 100, Game.HEIGHT / 2, 1);
    }

    public void setScore(int addcoin) {
        this.score += addcoin;
        displayScore.setText("Score: " + this.score);
    }

    public void setDisplayBooster(String booster) {
        displayBooster.setText("Booster: " + booster);
    }

    public void removeDisplayBooster() {
        displayBooster.setText("Booster: ");
    }

    public int getScore() {
        return score;
    }
}

