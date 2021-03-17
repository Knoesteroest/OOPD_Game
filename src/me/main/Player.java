package me.main;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;
import tiles.CoinTile;
import tiles.WallTile;

import java.util.List;

public class Player extends AnimatedSpriteObject implements ICollidableWithTiles {

    private Game game;

    private boolean[] keyDown = new boolean[4];

    private int speed;
    private int score;
    private float x,y;

    public Player(Game game){
     super(new Sprite(Game.MEDIA_URL.concat("player_run.gif")),2);
     this.game = game;

     setCurrentFrameIndex(1);
     for (int i =0; i< keyDown.length; i++) {
         keyDown[i] = false;
     }
     speed = 2;
    }

    public void addScore(int points){
        score += points;
        System.out.println("Score is nu: " + score);
    }

    @Override
    public void update() {

//        if (getX() <= 0) {
//            setxSpeed(0);
//            setX(0);
//        }
//        if (getY() <= 0) {
//            setySpeed(0);
//            setY(0);
//        }
//        if (getX() >= game.width - 15) {
//            setxSpeed(0);
//            setX(game.width - 15);
//        }
//        if (getY() >= game.height - 15) {
//            setySpeed(0);
//            setY(game.height - 15);
//        }



        x = getX();
        y = getY();

        x += getxSpeed();
        y += getySpeed();

//        x = game.clamp((int) x, 0, Game.WIDTH);
//        y = game.clamp((int) y, 0, Game.HEIGHT);

        //collision();
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
                setySpeed(-2);
                keyDown[0] = true;
                break;
            case 65: // A
                //setDirectionSpeed(270, speed);
                setCurrentFrameIndex(0);
                setxSpeed(-2);
                keyDown[1] = true;
                break;
            case 83: // S
                //setDirectionSpeed(180, speed);
                setySpeed(2);
                keyDown[2] = true;
                break;
            case 68: // D
                //setDirectionSpeed(90, speed);
                setxSpeed(2);
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
                        setX(vector.x + getWidth()+6);
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (CollisionSide.BOTTOM.equals(ct.getCollisionSide())) {
                    try {
                        vector = game.getTileMap().getTilePixelLocation(ct.getTile());
                        setY((vector.y + getWidth())+6);
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            } else if(ct.getTile() instanceof CoinTile){
                    if (CollisionSide.LEFT.equals(ct.getCollisionSide())
                      ||CollisionSide.RIGHT.equals(ct.getCollisionSide())
                      ||CollisionSide.TOP.equals(ct.getCollisionSide())
                      ||CollisionSide.BOTTOM.equals(ct.getCollisionSide())) {
                        try {
                            vector = game.getTileMap().getTilePixelLocation(ct.getTile());
                            game.getTileMap().setTile((int) vector.x / 35, (int) vector.y / 35, -1);
                        } catch (TileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
