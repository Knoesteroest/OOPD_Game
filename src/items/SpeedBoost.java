package items;

import me.main.Game;
import nl.han.ica.oopg.objects.Sprite;

public class SpeedBoost extends Booster{
    private final int boostedSpeed = 4;

    public SpeedBoost(Game game, Sprite sprite, int effectDuration) {
        super(game, new Sprite(Game.MEDIA_URL.concat("lightningBolt.png")), 5);
    }

    @Override
    public void startEffect() {
        game.getPlayer().setSpeed(boostedSpeed);
    }

    @Override
    public void endEffect() {
        game.getPlayer().resetSpeed();
    }

    @Override
    public void update() {}
}
