package me.main;

import Enemy.Enemy;
import Enemy.Zombie;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;
import tiles.CoinTile;
import tiles.WallTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game extends GameEngine{

    public static final int WIDTH = 945, HEIGHT = WIDTH / 12 * 9;
    public static final float centerX = ((WIDTH /35) * 17), centerY = ((HEIGHT /35) *15);
    public static final String MEDIA_URL = "src/media/";

    public static void main(String[] args) {
        Game game = new Game();
        game.runSketch();
    }

    @Override
    public void setupGame() {

        addGameObject(new Player(this),centerX,centerY);
        addGameObject(new Zombie(this),35,635);

        setView(WIDTH,HEIGHT);
        setMap();

    }

    @Override
    public void update() {
    }

    public void setMap(){

        Sprite wallSprite = new Sprite(Game.MEDIA_URL.concat("wallTile.png"));
        Sprite coinSprite = new Sprite(Game.MEDIA_URL.concat("coin.gif"));
        TileType<WallTile> wallTileType = new TileType<>(WallTile.class, wallSprite);
        TileType<CoinTile> cointTileType = new TileType<>(CoinTile.class,coinSprite);

        TileType[] tileTypes = {wallTileType, cointTileType};
        int tileSize = 35;
        int tilesMap[][] = {

                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,-1,-1,-1,-1,0,0,0,0,0,-1,0,-1,0,-1,-1,-1,-1,-1,0,-1,0,-1,-1,-1,0,0},
                {0,-1,0,0,-1,-1,-1,-1,-1,0,-1,0,-1,-1,-1,0,-1,0,-1,-1,-1,0,1,0,-1,0,0},
                {0,-1,1,-1,-1,0,-1,0,-1,0,-1,0,-1,0,-1,0,-1,0,0,0,-1,-1,-1,0,-1,0,0},
                {0,-1,0,-1,-1,-1,-1,0,-1,0,-1,-1,-1,0,-1,0,-1,-1,-1,0,0,-1,0,0,-1,0,0},
                {0,-1,0,-1,0,0,0,0,-1,-1,-1,0,-1,0,0,0,-1,0,-1,0,-1,-1,-1,0,-1,-1,0},
                {0,-1,-1,-1,0,0,-1,-1,-1,0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0,-1,0,0,-1,0},
                {0,-1,0,-1,-1,-1,-1,0,0,0,-1,0,0,-1,0,0,-1,0,-1,0,-1,-1,-1,-1,0,-1,0},
                {0,-1,0,0,0,0,-1,0,-1,-1,-1,0,-1,-1,-1,0,-1,0,-1,0,-1,0,-1,1,0,-1,0},
                {0,-1,0,0,0,0,-1,-1,-1,0,-1,0,-1,-1,-1,0,-1,-1,-1,0,-1,0,0,-1,-1,-1,0},
                {0,-1,-1,-1,-1,0,0,0,-1,-1,-1,0,0,0,0,0,0,0,-1,0,-1,-1,-1,0,0,-1,0},
                {0,-1,0,0,-1,0,-1,-1,-1,0,-1,-1,0,-1,-1,0,-1,0,-1,-1,-1,0,-1,0,-1,-1,0},
                {0,0,0,0,-1,-1,-1,0,-1,0,0,-1,0,0,-1,0,-1,-1,-1,0,-1,-1,-1,-1,-1,0,0},
                {0,-1,-1,0,-1,0,-1,-1,-1,0,0,-1,-1,0,-1,0,0,-1,0,0,0,0,-1,0,-1,-1,0},
                {0,-1,-1,-1,-1,0,-1,0,-1,0,0,0,-1,0,-1,-1,1,-1,-1,0,-1,0,-1,0,0,-1,0},
                {0,-1,0,0,-1,-1,-1,0,-1,-1,-1,-1,-1,-1,-1,0,-1,0,-1,0,-1,-1,-1,-1,0,-1,0},
                {0,-1,-1,-1,-1,0,-1,-1,-1,0,-1,0,-1,0,-1,0,-1,0,-1,0,-1,0,0,-1,-1,-1,0},
                {0,1,0,-1,0,0,-1,0,-1,-1,-1,0,-1,0,-1,-1,-1,-1,-1,0,-1,0,-1,-1,0,-1,0},
                {0,-1,-1,-1,-1,-1,-1,0,-1,0,-1,0,-1,-1,-1,0,-1,0,-1,-1,-1,-1,-1,0,0,-1,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }

    public void setView(int width, int height){
        setView(new View(width,height));
        size(width,height);
    }


    public static float clamp(float var, float min, float max){
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else return var;
    }

    public void updateObjectList(GameObject object,float x, float y){
        addGameObject(object,x,y);
    }
}
