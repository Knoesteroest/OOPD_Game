package items;
/**
 * This booster increases the player's speed to 4 for 5 seconds, then resets it.
 */

import HUD.HUD;
import me.main.Game;
import nl.han.ica.oopg.objects.Sprite;

public class SpeedBoost extends Booster {

    private final static Sprite boltSprite = new Sprite(Game.MEDIA_URL.concat("lightningBolt.png"));
    private final int boostedSpeed = 3;
    private final static double initEffectDuration = 5;
    private HUD hud;
    /**
     * Constructor
     * @param game The game object
     * @param hud The hud that is used to display the active booster.
     */
    public SpeedBoost(Game game, HUD hud) {
        super(game, boltSprite, initEffectDuration, BoosterId.Speed);
        this.hud = hud;
    }
    /**
     * Speeds up the player and displays this booster in the hud.
     */
    @Override
    public void startEffect() {
        game.getPlayer().setSpeed(boostedSpeed);
        hud.setDisplayBooster(getTypeId().toString());
    }
    /**
     * Resets the speed of the player and removes this booster from the hud.
     */
    @Override
    public void endEffect() {
        game.getPlayer().resetSpeed();
        hud.removeDisplayBooster();
    }

    //mandatory by OOPG
    @Override
    public void update() {
    }
}
