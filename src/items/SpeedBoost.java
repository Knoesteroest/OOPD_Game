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

    public SpeedBoost(Game game, HUD hud) {
        super(game, boltSprite, initEffectDuration, BoosterId.Speed);
        this.hud = hud;
    }

    @Override
    public void startEffect() {
        game.getPlayer().setSpeed(boostedSpeed);
        hud.setDisplayBooster(getTypeId().toString());
    }

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
