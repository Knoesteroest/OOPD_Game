package items;

/**
 * This booster slows all enemies to .5 speed for 5 seconds.
 * It gets a list of all enemies from Game.
 */

import enemy.Enemy;
import me.main.Game;
import me.main.ObjectTypeId;
import nl.han.ica.oopg.objects.Sprite;

import java.util.ArrayList;

public class FlashBomb extends Booster{

    private final static Sprite flashSprite = new Sprite(Game.MEDIA_URL.concat("flashbang.png"));
    private final static double initEffectDuration = 5;

    public FlashBomb(Game game) {
        super(game, flashSprite, initEffectDuration, ObjectTypeId.Flash);
    }

    @Override
    public void startEffect() {
        ArrayList<Enemy> allEnemies = game.getAllEnemies();
        for (Enemy enemy: allEnemies){
            enemy.setSpeed(0.5f);
        }
    }

    @Override
    public void endEffect() {
        ArrayList<Enemy> allEnemies = game.getAllEnemies();
        for (Enemy enemy: allEnemies){
            enemy.setSpeed(1.0f);
        }
    }

    //mandated by OOPG for some reason
    @Override
    public void update() {}
}
