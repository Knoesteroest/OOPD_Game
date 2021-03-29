package items;

import me.main.Game;
import me.main.Player;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

import java.util.List;

public abstract class Item extends SpriteObject /*implements ICollidableWithGameObjects*/ {
    protected Game game;

    public Item(Game game, Sprite sprite){
        super(sprite);
        this.game=game;
    }
    /*
        is called when the object collides with the player
        this object is destroyed after
     */
    public abstract void pickUp(Player player);
    public void touchPlayer(Player player){
        pickUp(player);
        game.deleteGameObject(this);
    }

}
