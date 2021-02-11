import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

public class PowerUp extends GameObject implements ICollidableWithGameObjects {
    final int kleur = 0xFFFF1493;
    //private MazeDash world;

    public PowerUp(float x, float y, float width, float height) {
        super(x, y, width, height);
        //this.world = world;
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> list) {
        if(!list.isEmpty()){
            Player speler = (Player) list.get(0);
            speler.kleur = 0xFFFFFF00;
            MazeDash.game.deleteGameObject(this);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics pGraphics) {
        pGraphics.fill(kleur);
        pGraphics.rectMode(CENTER);
        pGraphics.rect(x, y, width, height);
    }
}
