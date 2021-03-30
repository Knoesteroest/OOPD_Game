package items;
/**
 * This class is the super-class of all boosters and coins that can be picked up by the player.
 * When this happens the player object calls touchPlayer(), which then calls pickUp in
 * the sub-class and before removing itself from the game.
 */

import me.main.Game;
import me.main.Player;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;


public abstract class Item extends SpriteObject {
    protected Game game;

    public Item(Game game, Sprite sprite){
        super(sprite);
        this.game=game;
    }

    public abstract void pickUp(Player player);
    public void touchPlayer(Player player){
        pickUp(player);
        game.deleteGameObject(this);
    }

}
