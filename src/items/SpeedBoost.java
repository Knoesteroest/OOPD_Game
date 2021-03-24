package items;

import me.main.Game;
import me.main.ObjectTypeId;
import nl.han.ica.oopg.objects.Sprite;

public class SpeedBoost extends Booster{

    private final static Sprite boltSprite = new Sprite(Game.MEDIA_URL.concat("lightningBolt.png"));
    private final int boostedSpeed = 4;
    private final static int initEffectDuration = 5;

    public SpeedBoost(Game game) {
        super(game, boltSprite, initEffectDuration, ObjectTypeId.Speed);
    }

    @Override
    public void startEffect() {
        game.getPlayer().setSpeed(boostedSpeed);
    }

    @Override
    public void endEffect() {
        game.getPlayer().resetSpeed();
    }

    //mandatory by OOPG for some reason
    @Override
    public void update() {}
}
