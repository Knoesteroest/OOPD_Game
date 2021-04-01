package HUD;

import me.main.Game;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.TextObject;
import processing.core.PGraphics;


public class HUD extends Dashboard {
    private HealthBar HB;
    private TextObject displayScore;
    private TextObject displayBooster;
    private Game game;

    private final int layerPosition = 1;
    private int score;

    public HUD(int width, int height, Game game) {
        super(0, 0, width, height);
        this.game = game;
        HB = new HealthBar();

        final int fontSize = 22;
        final int offsetDashBoardText = 225;
        final int offsetDashBoardItemHeight = 3;

        displayScore = new TextObject("Score: 0", fontSize);
        displayBooster = new TextObject("Booster: ", fontSize);

        super.addGameObject(HB, 7, offsetDashBoardItemHeight, layerPosition);
        super.addGameObject(displayScore, offsetDashBoardText, offsetDashBoardItemHeight, layerPosition);
        super.addGameObject(displayBooster, width - offsetDashBoardText, offsetDashBoardItemHeight, layerPosition);
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
        final int fontSize = 50;

        final int offsetEndscreenTitle = 140;
        final int offsetEndscreenScore = 100;

        final int devideInHalf = 2;
        final int devideInQuarter = 4;

        TextObject title = new TextObject("Game Over", fontSize);
        displayScore = new TextObject("Score: " + getScore(), fontSize);

        super.addGameObject(title, Game.WIDTH / devideInHalf - offsetEndscreenTitle, Game.HEIGHT / devideInQuarter, layerPosition);
        super.addGameObject(displayScore, Game.WIDTH / devideInHalf - offsetEndscreenScore, Game.HEIGHT / devideInHalf, layerPosition);
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

