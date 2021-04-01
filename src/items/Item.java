package items;

/**
 * This class is the super-class of all boosters and coins that can be picked up by the player.
 * When this happens the player object calls touchPlayer(), which then calls pickUp in
 * the sub-class instance before removing itself from the game.
 */

import me.main.Game;
import me.main.Player;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public abstract class Item extends SpriteObject {

    protected Game game;

    /**
     * Constructor
     * @param game The game object.
     * @param sprite The sprite used for this item.
     */
    public Item(Game game, Sprite sprite){
        super(sprite);
        this.game = game;
    }

    /**
     * Gets called when a player touches this item. After this runs the Item is removed from the game.
     * @param player The player object that touched this item.
     */
    public abstract void pickUp(Player player);

    /**
     * Calls pickUp in the sub-class and removes itself from the game after.
     * @param player The player object that touched this item.
     */
    public void touchPlayer(Player player){
        pickUp(player);
        game.deleteGameObject(this);
    }
}
