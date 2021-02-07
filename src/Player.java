import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Player extends GameObject{

    final int kleur = 0xFF0000FF;
    public Player(float x, float y, float width, float height){
        super( x,  y,  width,  height);
        setDirectionSpeed(0,2);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics pGraphics) {
        pGraphics.fill(kleur);
        pGraphics.rectMode(CENTER);
        pGraphics.rect(x,y,width,height);
    }
}
