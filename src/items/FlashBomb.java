package items;



import HUD.HUD;
import enemy.Enemy;
import me.main.Game;
import nl.han.ica.oopg.objects.Sprite;

import java.util.ArrayList;
/**
 * This booster slows all enemies to .5 speed for 5 seconds, then resets the speed back.
 * It gets a list of all enemies from Game.
 */
public class FlashBomb extends Booster {

    private final static Sprite flashSprite = new Sprite(Game.MEDIA_URL.concat("flashbang.png"));
    private final static double initEffectDuration = 5;
    private final float slowedSpeed = 0.5f;

    private HUD hud;

    /**
     * Constructor
     * @param game The game object
     * @param hud The hud that is used to display the active booster.
     */
    public FlashBomb(Game game, HUD hud) {
        super(game, flashSprite, initEffectDuration, BoosterId.Flash);
        this.hud = hud;
    }

    /**
     * Slows all enemies and displays this booster in the hud.
     */
    @Override
    public void startEffect() {
        ArrayList<Enemy> allEnemies = game.getAllEnemies();
        hud.setDisplayBooster(getTypeId().toString());
        for (Enemy enemy : allEnemies) {
            enemy.setSpeed(slowedSpeed);
        }
    }

    /**
     * Resets the speed of all enemies and removes this booster from the hud.
     */
    @Override
    public void endEffect() {
        ArrayList<Enemy> allEnemies = game.getAllEnemies();
        hud.removeDisplayBooster();
        for (Enemy enemy : allEnemies) {
            enemy.resetSpeed();
        }
    }

    //mandated by OOPG
    @Override
    public void update() {
    }
}
