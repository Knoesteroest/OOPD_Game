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

public class CircularSaw extends Enemy{
    private final static Sprite sprite = new Sprite(Game.MEDIA_URL.concat("sawAnim.png"));
    private final static float sawDamage = 0.75f;
    private final static float initialSpeed = 1.0f;

    public CircularSaw(Game game) {
        super(sprite, 3, game, sawDamage, initialSpeed);
        setCurrentFrameIndex(0);
        setSpeed(initialSpeed);
        setDirection(0);
//        testTrigonometry();
    }

    private void turnLeft(){
        setDirection((getDirection() - 90) % 360);
//        System.out.println("Turning left.");
    }
    private void turnRight(){
        setDirection((getDirection() + 90) % 360);
//        System.out.println("Turning right.");
    }
    private Tile getLeftTile() {
        Tile tile = getAdjacentTile(getTile(), getDirection(), -90);
        return tile;
    }
    /**
     * Update runs every frame.
     * It checks if the saw is in the middle of the tile then,
     * turns left if there is an empty space there, if not,
     * turns right if there is a wall ahead.
     * (If it then bumps into a wall again, collission should keep it in the middle of the tile
     * and run this again next frame).
     * It also switches the frame of the rotate animation of the saw.
     */
    @Override
    public void update() {
        super.update();
        nextFrame();
    }

    @Override
    protected void choosePath() {
        //System.out.println("Saw is on tile: " + (int) map.getTileIndex( getTile()).x +":"+ (int) map.getTileIndex(getTile()).y);
        if (getLeftTile() instanceof EmptyTile) {
            turnLeft();
        } else if (getFrontTile() instanceof WallTile) {
            turnRight();
        }
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
}
