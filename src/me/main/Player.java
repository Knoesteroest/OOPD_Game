package me.main;

/**
 * This is the class for the player. It keeps track of held keys and moves the player.
 * It is collidible with tiles to stop it from entering walls.
 * It is the only object that handles object collission, which it uses to pick up
 * boosters and get damaged by enemies.
 * It keeps track of player hitpoints and score.
 * It is created at game start by ObjectSpawner
 */

import enemy.Enemy;
import items.Item;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;
import tiles.PlayerSpawnTile;
import tiles.WallTile;

import java.util.Arrays;
import java.util.List;

public class Player extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {

    private Game game;
    private Maze map;
    private Difficulty difficulty;

    private boolean[] keyDown = new boolean[4];
    private final static int initialSpeed = 2;
    private int speed;
    private int score;
    private final static int initialHitpoints = 400;
    private int hitpoints;

    private PlayerSpawnTile spawn;
    private boolean inSpawn;

    /**
     * Creator for a Player object.
     * @param game The central Game object.
     * @param difficulty The difficulty object used to increase difficulty
     * @param spawn The PlayerSpawnTile, this is not used for the location of the created
     *              Player object, only to check if the player has left the spawn.
     */
    public Player(Game game, Difficulty difficulty, PlayerSpawnTile spawn){
        super(new Sprite(Game.MEDIA_URL.concat("player_run.gif")),2);
        this.game = game;
        this.map = (Maze) game.getTileMap();
        this.difficulty = difficulty;
        setCurrentFrameIndex(1);
        Arrays.fill(keyDown,false);
        speed = initialSpeed;
        hitpoints = initialHitpoints;
        this.spawn = spawn;
        inSpawn = true;
        }

    /**
     * Adds points to the player score total, then checks if that
     * @param points The number of points to add.
     */
    public void addScore(int points){
        int previousScore = score;
        score += points;
        difficulty.scoreThresholdCrossed (score, previousScore);
        System.out.println("Score is nu: " + score);
    }

    public void setSpeed(int speed){
        this.speed = speed;
//        System.out.println("Snelheid is nu: " + speed);
    }

    public void resetSpeed(){
        setSpeed(initialSpeed);
    }

    /**
     * Moves the player and checks if he has left the spawn.
     * If the player left his spawn the spawntile is closed by Maze.
     */
    @Override
    public void update() {

//        x = getX();
//        y = getY();
//
//        x += getxSpeed();
//        y += getySpeed();

        if(spawn != null && !inSpawn){
            map.closeSpawn();
            spawn = null;
        }
    }

    @Override
    public void keyPressed(int keyCode, char key){
        /**
         * W : 87
         * A : 65
         * S : 83
         * D : 68
         */
        switch (keyCode){
            case 87: // W
                // setDirectionSpeed(0, speed);
                setySpeed(-speed);
                keyDown[0] = true;
                break;
            case 65: // A
                //setDirectionSpeed(270, speed);
                setCurrentFrameIndex(0);
                setxSpeed(-speed);
                keyDown[1] = true;
                break;
            case 83: // S
                //setDirectionSpeed(180, speed);
                setySpeed(speed);
                keyDown[2] = true;
                break;
            case 68: // D
                //setDirectionSpeed(90, speed);
                setxSpeed(speed);
                keyDown[3] = true;
                setCurrentFrameIndex(1);
                break;
        }
    }

    @Override
    public void keyReleased(int keyCode, char key){
        if (keyCode == 87) keyDown[0] = false;
        if (keyCode == 65) keyDown[1] = false;
        if (keyCode == 83) keyDown[2] = false;
        if (keyCode == 68) keyDown[3] = false;

        if (!keyDown[0] && !keyDown[2]) setySpeed(0);
        if (!keyDown[1] && !keyDown[3]) setxSpeed(0);
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        PVector vector;
        inSpawn = false;
        for (CollidedTile ct : collidedTiles) {
            if (ct.getTile() instanceof WallTile) {
                if (CollisionSide.TOP.equals(ct.getCollisionSide())) {
                    try {
                        vector = game.getTileMap().getTilePixelLocation(ct.getTile());
                        setY(game.clamp(vector.y - getHeight(),0,Game.HEIGHT));
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
            } else if (spawn != null && ct.getTile() == spawn){
                inSpawn = true;
            }
        }
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> list) {
        for(GameObject collidedObject: list){
            if(collidedObject instanceof Item){
                ((Item) collidedObject).touchPlayer(this);
            }
            if(collidedObject instanceof Enemy){
                takeDamage(((Enemy)collidedObject).getDamage());
            }
        }
    }

    private void takeDamage(int damage){
        hitpoints -= damage;
        System.out.println("HP:"+hitpoints);
    }
}
