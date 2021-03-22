package items;

import items.PickUp;
import me.main.Game;
import me.main.Player;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

import java.util.List;

public class Coin extends PickUp {
    private final int value = 1;

    public Coin(Game game, PVector coordinates) {
        super(game, new Sprite(Game.MEDIA_URL.concat("coin.gif")));
        this.setX(coordinates.x);
        this.setY(coordinates.y);
    }

    public void pickUp(Player player){
        player.addScore(value);
    }

    @Override
    public void update() {

    }


}
