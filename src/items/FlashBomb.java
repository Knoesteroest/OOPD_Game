package items;

import enemy.Enemy;
import me.main.Game;
import me.main.ObjectTypeId;
import nl.han.ica.oopg.objects.Sprite;

import java.util.ArrayList;

public class FlashBomb extends Booster{

    private final static Sprite flashSprite = new Sprite(Game.MEDIA_URL.concat("flashbang.png"));
    private final static int initEffectDuration = 5;

    public FlashBomb(Game game) {
        super(game, flashSprite, initEffectDuration, ObjectTypeId.Speed);
    }

    @Override
    public void startEffect() {
        ArrayList<Enemy> allEnemies = game.getAllEnemies();
        allEnemies.forEach(enemy -> setSpeed(.5f));
    }

    @Override
    public void endEffect() {
        ArrayList<Enemy> allEnemies = game.getAllEnemies();
        allEnemies.forEach(enemy -> setSpeed(1));
    }

    //mandatory by OOPG for some reason
    @Override
    public void update() {}
}
