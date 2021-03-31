package HUD;

import me.main.Game;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class scoreBoard extends GameObject {
    private int score = 0;
    private HUD hud;

    scoreBoard(HUD hud){
        this.hud = hud;
    }
    @Override
    public void update() {}

    @Override
    public void draw(PGraphics g) {}



}
