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
//        setHeight(30);
//        setWidth(30);
        setCurrentFrameIndex(0);
        this.setSpeed(initialSpeed);
        setDirection(0);
//        testTrigonometry();
    }

    public void turnLeft(){
        setDirection((getDirection() - 90) % 360);
//        System.out.println("Turning left.");
    }
    public void turnRight(){
        setDirection((getDirection() + 90) % 360);
//        System.out.println("Turning right.");
    }

    /**
     * This runs every frame.
     * It checks if the saw is in the middle of the tile then,
     * turns left if there is an empty space there, if not,
     * turns right if there is a wall ahead.
     * (If it then bumps into a wall again, collission should keep it in the middle of the tile
     * and run this again next frame).
     * It also switches the frame of the rotate animation of the saw.
     */
    @Override
    public void update() {
        if (isMiddleOfTile()){
//            System.out.println("Saw is on tile: " + (int) map.getTileIndex( getTile()).x +":"+ (int) map.getTileIndex(getTile()).y);
            if (getLeftTile() instanceof EmptyTile) {
                turnLeft();
            } else if (getFrontTile() instanceof WallTile) {
                turnRight();
            }
        }
        nextFrame();
    }

    public void testTrigonometry(){
        Tile testTile = map.getTileOnIndex((int)5.0,(int)5.0);
        Tile resultTile = getAdjacentTile(testTile, 180, 0);
        PVector index = map.getTileIndex(resultTile);
        System.out.println("the tile in front of 5,5 going down is " + index.x +":"+ index.y);

        resultTile = getAdjacentTile(testTile, 0, -90);
        index = map.getTileIndex(resultTile);
        System.out.println("the tile  left of 5,5 going up is " + index.x +":"+ index.y);

        resultTile = getAdjacentTile(testTile, 270, -90);
        index = map.getTileIndex(resultTile);
        System.out.println("the tile  left of 5,5 going left is " + index.x +":"+ index.y);

        resultTile = getAdjacentTile(testTile, 180, -90);
        index = map.getTileIndex(resultTile);
        System.out.println("the tile  left of 5,5 going down is " + index.x +":"+ index.y);
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
        return map.getTileOnIndex(round(index.x), round(index.y));
    }

    private Tile getFrontTile() {
        Tile tile = getAdjacentTile(getTile(), getDirection(), 0);
        return tile;
    }

    private Tile getLeftTile() {
        Tile tile = getAdjacentTile(getTile(), getDirection(), -90);
        return tile;
    }
    /**
    Gets the tile this saw is on.
     */
    private Tile getTile(){
        Tile tile = map.getTileOnPosition((int) getCenterX(), (int) getCenterY());
        return tile;
    }

    private boolean isMiddleOfTile(){
        PVector tileCoordinates = map.getTilePixelLocation(getTile());
        float distance = PVector.dist(new PVector(getX(), getY()), tileCoordinates);
        return (distance < 1.0); //Is 1.0 close enough?
    }
}
