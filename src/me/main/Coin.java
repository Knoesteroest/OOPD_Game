package me.main;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PVector;

import java.util.List;

public class Coin extends SpriteObject implements ICollidableWithGameObjects {
    private final int value = 1;
    private Game game;

    public Coin(Game game, PVector coordinates) {
        super(new Sprite(Game.MEDIA_URL.concat("coin.gif")));
        this.game = game;

        this.setX(coordinates.x);
        this.setY(coordinates.y);
    }

    public void pickUp(Player player){
        player.addScore(value);
        game.deleteGameObject(this);
    }

    @Override
    public void update() {

    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> list) {
        for(GameObject collidedObject: list ){
            if(collidedObject instanceof Player){
                pickUp((Player) collidedObject);
            }
        }
    }
}
