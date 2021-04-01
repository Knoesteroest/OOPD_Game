package items;

import me.main.Game;
import me.main.Player;
import nl.han.ica.oopg.objects.Sprite;
/**
 * The coin class simply provides points for the player when picked up.
 * It is removed afterwards by its super-class: Item.
 */
public class Coin extends Item {
    private final int value = 1;

    /**
     * Constructs a new Coin object.
     * @param game The Game object.
     */
    public Coin(Game game) {
        super(game, new Sprite(Game.MEDIA_URL.concat("coin.gif")));
    }

    /**
     This adds the coin's value to the player's score.
     This is called by Player when picked up.
     The Item class removes this Booster from the game after this runs.
     @param player The player object
     */
    public void pickUp(Player player){
        player.addScore(value);
    }

    //mandated by OOPG
    @Override
    public void update() {}
}
