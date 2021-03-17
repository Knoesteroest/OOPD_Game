package enemy;

import me.main.Game;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;
import tiles.WallTile;

import java.util.List;

/**
 * Class that contains all basic structure of the enemy
 */
public class Enemy extends AnimatedSpriteObject implements ICollidableWithTiles {

    private Game game;
    public Enemy(Sprite sprite, int totalFrames,Game game) {
        super(sprite, totalFrames);

        this.game = game;
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> list) {
        PVector vector;


        for (CollidedTile ct : list) {
//            if (ct.getTile() instanceof WallTile) {
//                if (CollisionSide.TOP.equals(ct.getCollisionSide())) {
//                    try {
//                        vector = game.getTileMap().getTilePixelLocation(ct.getTile());
//                        setY(game.clamp(vector.y - getHeight() +10, 0, Game.HEIGHT));
//                    } catch (TileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (CollisionSide.RIGHT.equals(ct.getCollisionSide())) {
//                    try {
//                        vector = game.getTileMap().getTilePixelLocation(ct.getTile());
//                        setX(game.clamp(vector.x + getWidth(), 0, Game.HEIGHT));
//                    } catch (TileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (CollisionSide.LEFT.equals(ct.getCollisionSide())) {
//                    try {
//                        vector = game.getTileMap().getTilePixelLocation(ct.getTile());
//                        setX(game.clamp(vector.x - getWidth(), 0, Game.HEIGHT));
//                    } catch (TileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (CollisionSide.BOTTOM.equals(ct.getCollisionSide())) {
//                    try {
//                        vector = game.getTileMap().getTilePixelLocation(ct.getTile());
//                        setX(game.clamp(vector.y - getHeight() + 10, 0, Game.HEIGHT));
//                    } catch (TileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
            if (ct.getTile() instanceof WallTile) {
                if (CollisionSide.TOP.equals(ct.getCollisionSide())) {
                    try {
                        vector = game.getTileMap().getTilePixelLocation(ct.getTile());
                        setY(game.clamp(vector.y - getHeight(), 0, Game.HEIGHT));
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (CollisionSide.LEFT.equals(ct.getCollisionSide())) {
                    try {
                        vector = game.getTileMap().getTilePixelLocation(ct.getTile());
                        setX(vector.x - getWidth());
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (CollisionSide.RIGHT.equals(ct.getCollisionSide())) {
                    try {
                        vector = game.getTileMap().getTilePixelLocation(ct.getTile());
                        setX(vector.x + getWidth() + 6);
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (CollisionSide.BOTTOM.equals(ct.getCollisionSide())) {
                    try {
                        vector = game.getTileMap().getTilePixelLocation(ct.getTile());
                        setY((vector.y + getWidth()) + 6);
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            }
    }

    @Override
    public void update() {
    }
}
