package enemy;

/**
 * A circular saw is an enemy that always turns left.
 * Whenever it is in the middle of the tile, it checks adjacent tiles to see if it should turn.
 * Object collission is handled in the Player object.
 */

import me.main.Game;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.EmptyTile;
import nl.han.ica.oopg.tile.Tile;
import processing.core.PVector;
import tiles.WallTile;

import static processing.core.PApplet.*;

public class CircularSaw extends Enemy{
    private final static Sprite sprite = new Sprite(Game.MEDIA_URL.concat("sawAnim.png"));
    private final static int sawDamage = 3;
    private final static float initialSpeed = 1.0f;

    public CircularSaw(Game game) {
        super(sprite, 3, game, sawDamage);
        setCurrentFrameIndex(0);
        this.setSpeed(initialSpeed);
        setDirection(0);
    }

    public void turnLeft(){
        setDirection((getDirection() - 90) % 360);
        //System.out.println("Turning left.");
    }
    public void turnRight(){
        setDirection((getDirection() + 90) % 360);
        //System.out.println("Turning right.");
    }

    /**
     * This is run every frame.
     * It checks if the saw is in the middle of the tile,
     * turns left if there is an empty space there,
     * turns right if there is a wall ahead.
     * It also switches the frame of the rotate animation of the saw.
     */
    @Override
    public void update() {
        if (isMiddleOfTile()){
//            System.out.println("In the middle");
            if (getLeftTile() instanceof EmptyTile) {
                turnLeft();
            } else if (getFrontTile() instanceof WallTile) {
                turnRight();
            }
        }
        nextFrame();
    }

    public void testTrigonometry(){
        Tile testTile = map.getTileOnIndex(5,5);
        Tile resultTile = getAdjacentTile(testTile, 0, 0);
        PVector index = map.getTileIndex(resultTile);
        System.out.println("the tile in front of 5,5 going up is " + index.x +":"+ index.y);

        resultTile = getAdjacentTile(testTile, 0, -90);
        index = map.getTileIndex(resultTile);
        System.out.println("the tile in left of 5,5 going up is " + index.x +":"+ index.y);

        resultTile = getAdjacentTile(testTile, 270, -90);
        index = map.getTileIndex(resultTile);
        System.out.println("the tile in left of 5,5 going left is " + index.x +":"+ index.y);

        resultTile = getAdjacentTile(testTile, 180, -90);
        index = map.getTileIndex(resultTile);
        System.out.println("the tile in left of 5,5 going down is " + index.x +":"+ index.y);
    }

    /**
     * This gets a tile adjacent to the given tile, to a specified side, relative to our movement direction.
     * @param tile The tile this saw is on.
     * @param heading The direction (in degrees) the saw is moving in.
     * @param side The direction (in degrees) of the tile to return, relative to your heading. (-90 for left, 90 for right)
     * @return The adjacent tile specified.
     */
    private Tile getAdjacentTile(Tile tile, float heading, float side) {
        PVector index = map.getTileIndex(tile);
        index.x += sin(radians(heading + side));
        index.y -= cos(radians(heading + side));
        return map.getTileOnIndex((int)index.x, (int) index.y);
    }

    private Tile getFrontTile() {
        Tile tile = getAdjacentTile(getTile(), getDirection(), 0);
        PVector index = map.getTileIndex(getTile());
//        System.out.println("frontTile: " + index.x +":"+ index.y);
        return tile;
    }

    private Tile getLeftTile() {
        Tile tile = getAdjacentTile(getTile(), getDirection(), -90);
        PVector index = map.getTileIndex(getTile());
//        System.out.println("leftTile: " + index.x +":"+ index.y);
        return tile;
    }
    /**
    Gets the tile this saw is on.
     */
    private Tile getTile(){
        Tile tile = map.getTileOnPosition((int) getCenterX(), (int) getCenterY());
//        System.out.println("ourTile: " + map.getTileIndex(tile).x +":"+ map.getTileIndex(tile).y);
        return tile;
    }

    private boolean isMiddleOfTile(){
        PVector tileCoordinates = map.getTilePixelLocation(getTile());
        return (PVector.dist(new PVector(getX(), getY()), tileCoordinates) < 0.5); //Is 0.5 close enough?
    }
}
