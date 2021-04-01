package enemy;

import me.main.Game;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;
import tiles.PlayerSpawnTile;
import tiles.WallTile;

import java.util.ArrayList;

/**
 * Class for the zombie
 * This enemy tries to follow the player and cannot go through walls.
 */
public class Zombie extends Enemy {

    private final static float zombieDamage = 0.5f;
    private final static float initialSpeed = 0.85f;

    /**
     * Makes the Zombie Sets an image,speed, beginning frame, and starting direction.
     * @param game the central class
     */
    public Zombie(Game game) {
        super(new Sprite(Game.MEDIA_URL.concat("zombie.gif")), 4, game, zombieDamage, initialSpeed);
        setCurrentFrameIndex(1);
        setDirection(180);
    }
    /**
     *  Sets the zombie's next step based on the player's position.
     */
    @Override
    protected void choosePath() {
        GameObject player = game.getPlayer();

        float diffX = this.getCenterX() - player.getCenterX();
        float diffY = this.getCenterY() - player.getCenterY();

        ArrayList<Integer> directionPreferences = getDirectionPreferences(diffX, diffY);

        for (int direction: directionPreferences) {
            if (isDirectionPassable(direction)) {
                setDirection(direction);
                move();
                break;
            }
        }
    }

    /**
     * This sorts the 4 orthogonal directions to move in by
     * the shortest route to the player.
     * @param diffX Distance between the player and the zombie over the x axis
     * @param diffY Distance between the player and the zombie over the y axis
     * @return An ArrayList of 4 integers
     */
    private ArrayList<Integer> getDirectionPreferences(float diffX, float diffY){
        ArrayList<Integer> directionPreferences = new ArrayList<>();
        int zombieFacingX;
        int zombieFacingY;
        if(Math.abs(diffX) > Math.abs(diffY)){ // If horizontal distance larger than vertical
            zombieFacingX = setHorizontal(diffX);// then pick the horizontal direction towards player first
            zombieFacingY = setVertical(diffY);
        }else{
            zombieFacingX = setVertical(diffY); //else pick vertical first
            zombieFacingY = setHorizontal(diffX);
        }
        directionPreferences.add(zombieFacingX);
        directionPreferences.add(zombieFacingY);
        directionPreferences.add(setReversedDirection(zombieFacingY)); //if the zombie has to move in another direction do it by the less favoured axis
        directionPreferences.add(setReversedDirection(zombieFacingX));
        return directionPreferences;
    }

    /**
     * Tests if the zombie can move in the specified direction by checking if the tile is clear
     * and if it collided with a tile in the direction last frame.
     * @param direction Direction in degrees
     * @return true if we can move in the direction, false if not
     */
    private boolean isDirectionPassable(int direction){
        Tile zombieTile = getTile();
        Tile otherTile = getAdjacentTile(zombieTile, direction,0);
        if (otherTile instanceof WallTile || otherTile instanceof PlayerSpawnTile){
            return false;
        }
        return true;
    }

    /**
     * Sets image direction based on movement
     * @param diffX the difference between the zombie and player over the x axis
     * @return whether the zombie is moving left or right
     */
    private int setHorizontal(float diffX){
        if(diffX > 0) {
            return 270;
        }else{
            return 90;
        }
    }
    /**
     * Sets image direction based on movement
     * @param diffY the difference between the zombie and player over the y axis
     * @return whether the zombie is moving up or down
     */
    private int setVertical(float diffY){
        if(diffY > 0) {
            return 0;
        }else{
            return 180;
        }
    }

    /**
     * Sets zombies direction to opposite side.
     * @param direction current direction the zombie is moving
     * @return opposite side of the zombies direction
     */
    private int setReversedDirection(int direction){
        return (direction + 180) % 360;
    }
}
