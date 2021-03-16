package me.main;

import Enemy.Enemy;
import Enemy.Zombie;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;
import tiles.WallTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game extends GameEngine{
    private ObjectSpawner objectSpawner;

    public static final int WIDTH = 945, HEIGHT = WIDTH / 12 * 9;
    public static final float centerX = ((WIDTH /35) * 17), centerY = ((HEIGHT /35) *15);
    public static final String MEDIA_URL = "src/media/";
    private Handler handler;

    public static void main(String[] args) {
        Game game = new Game();
        game.runSketch();
    }

    @Override
    public void setupGame() {
       handler = new Handler();

        handler.addObject(new Player(this,handler),centerX,centerY,this);
        handler.addObject(new Zombie(this,handler),35,635,this);

        setView(new View(WIDTH,HEIGHT));
        size(WIDTH,HEIGHT);

        objectSpawner = new ObjectSpawner(this, tileMap);
        objectSpawner.initCoins();
    }

    @Override
    public void update() {
    }

    public void setMap(){

        Sprite wallSprite = new Sprite(Game.MEDIA_URL.concat("wallTile.png"));
        TileType<WallTile> wallTileType = new TileType<>(WallTile.class, wallSprite);

        //Nummertjes die hij niet kent maakt de tilemap vanzelf EmptyTiles van.

        TileType[] tileTypes = {wallTileType/*, coinTileType*/};
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
