package enemy;

import me.main.Game;
import me.main.Maze;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;
import processing.core.PVector;
import tiles.PlayerSpawnTile;
import tiles.WallTile;

import java.util.List;

import static processing.core.PApplet.*;
import static processing.core.PApplet.round;

/**
 * Class that contains all basic structure of the two enemies: CircularSaw and Zombie
 * Makes sure sub-classes set damage and stay out of the walls
 */
public abstract class Enemy extends AnimatedSpriteObject implements ICollidableWithTiles {
    protected Game game;
    protected Maze map;

    protected float damage;
    protected float initialSpeed;

    public Enemy(Sprite sprite, int totalFrames, Game game, float damage, float initialSpeed) {
        super(sprite, totalFrames);
        this.game = game;
        this.map = (Maze) game.getTileMap();
        this.damage = damage;
        this.initialSpeed = initialSpeed;
        setSpeed(initialSpeed);
    }

    public void resetSpeed() {
        setSpeed(initialSpeed);
    }

    public float getDamage() {
        return damage;
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> list) {
        PVector tileCoordinates;

        for (CollidedTile collidedTile : list) {
            Tile tile = collidedTile.getTile();

            if (tile instanceof WallTile || tile instanceof PlayerSpawnTile) {
                CollisionSide collisionSide = collidedTile.getCollisionSide();

                if (CollisionSide.TOP.equals(collisionSide)) {
                    try {
                        tileCoordinates = game.getTileMap().getTilePixelLocation(collidedTile.getTile());
                        setY(tileCoordinates.y - getHeight());
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (CollisionSide.LEFT.equals(collisionSide)) {
                    try {
                        tileCoordinates = game.getTileMap().getTilePixelLocation(collidedTile.getTile());
                        setX(tileCoordinates.x - getWidth());
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (CollisionSide.RIGHT.equals(collisionSide)) {
                    try {
                        tileCoordinates = game.getTileMap().getTilePixelLocation(collidedTile.getTile());
                        setX(tileCoordinates.x + getWidth() + 6);
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (CollisionSide.BOTTOM.equals(collisionSide)) {
                    try {
                        tileCoordinates = game.getTileMap().getTilePixelLocation(collidedTile.getTile());
                        setY((tileCoordinates.y + getWidth()) + 6);
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /*
     Checks if the enemy is in the middle of the tile and if so, activates pathfinding.
     */
    @Override
    public void update() {
        if (isMiddleOfTile()) {
            choosePath();
        }
    }

    protected abstract void choosePath();

    /**
     * Gets the tile this enemy is on.
     */
    protected Tile getTile() {
        Tile tile = map.getTileOnPosition((int) getCenterX(), (int) getCenterY());
        return tile;
    }

    protected Tile getFrontTile() {
        Tile tile = getAdjacentTile(getTile(), getDirection(), 0);
        return tile;
    }

    /**
     * This gets a tile adjacent to the given tile, to a specified side, relative to our movement direction.
     *
     * @param tile    The tile this saw is on.
     * @param heading The direction (in degrees) the saw is moving in.
     * @param side    The direction (in degrees) of the tile to return, relative to your heading. (-90 for left, 90 for right)
     * @return The adjacent tile specified.
     */
    protected Tile getAdjacentTile(Tile tile, float heading, float side) {
        PVector index = map.getTileIndex(tile);
        index.x += sin(radians(heading + side));
        index.y -= cos(radians(heading + side));
        return map.getTileOnIndex(round(index.x), round(index.y));
    }

    /**
     * Checks if this enemy is in the middle of the tile, ready to make decisions about its path
     */
    protected boolean isMiddleOfTile() {

        PVector tileCoordinates = map.getTilePixelLocation(getTile());
        tileCoordinates.x += map.getTileSize() / 2.0f;
        tileCoordinates.y += map.getTileSize() / 2.0f;
        float distance = PVector.dist(new PVector(getCenterX(), getCenterY()), tileCoordinates);
        if (distance < getSpeed()) {
            setX(tileCoordinates.x - getWidth() / 2.0f);
            setY(tileCoordinates.y - getHeight() / 2.0f);
            return true;
        } else {
            return false;
        }
    }

    // TEST COMMENT
    public void testGetAdjacentTile() {
        Tile testTile = map.getTileOnIndex((int) 5.0, (int) 5.0);
        Tile resultTile = getAdjacentTile(testTile, 180, 0);
        PVector index = map.getTileIndex(resultTile);
        System.out.println("the tile in front of 5,5 going down is " + index.x + ":" + index.y);

        resultTile = getAdjacentTile(testTile, 0, -90);
        index = map.getTileIndex(resultTile);
        System.out.println("the tile  left of 5,5 going up is " + index.x + ":" + index.y);

        resultTile = getAdjacentTile(testTile, 270, -90);
        index = map.getTileIndex(resultTile);
        System.out.println("the tile  left of 5,5 going left is " + index.x + ":" + index.y);

        resultTile = getAdjacentTile(testTile, 180, -90);
        index = map.getTileIndex(resultTile);
        System.out.println("the tile  left of 5,5 going down is " + index.x + ":" + index.y);
    }
}
