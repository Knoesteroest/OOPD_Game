package HUD;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.TextObject;
import org.w3c.dom.Text;
import processing.core.PGraphics;

import java.awt.*;


public class HUD extends Dashboard {
    private HealthBar HB;
    private scoreBoard SB;
    private TextObject totalScore;
    private int totScore;

    public HUD(int width, int height) {
        super(0, 0, width, 35);
        HB = new HealthBar();
        SB = new scoreBoard();
        super.addGameObject(HB, 7, 3, 1);
//        totScore = new TextObject("Score:"+ SB.getscore, 22);
//        totalScore.setForeColor(188,188,188,188);
        super.addGameObject(SB, 225, 3, 2);
        super.setBackground(255,255,255);

        System.out.println("DASH ADDED");
    }

    @Override
    public void update() {
        HB.update();
    }

    @Override
    public void draw(PGraphics hud) {
        super.draw(hud);
        SB.draw(hud);
    }
}

