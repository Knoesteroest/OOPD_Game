package items;

import me.main.Game;
import me.main.Player;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

public class Coin extends Item {
    private final int value = 1;

    public Coin(Game game) {
        super(game, new Sprite(Game.MEDIA_URL.concat("coin.gif")));
    }

    public void pickUp(Player player){
        player.addScore(value);
    }

    @Override
    public void update() {

    }
}
