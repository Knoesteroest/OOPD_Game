package me.main;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.tile.EmptyTile;
import nl.han.ica.oopg.tile.Tile;
import nl.han.ica.oopg.tile.TileMap;
import processing.core.PVector;
import java.util.ArrayList;

public class ObjectSpawner implements IAlarmListener {
    private Maze map;
    private Game game;
    private Alarm coinTimer;
    private final String coinTimerName = "coinTimer";
    private final double coinSpawnInterval = 5;
    private final int startingCoins = 10;

    public ObjectSpawner(Game game, TileMap map){
        this.game = game;
        this.map = (Maze) map;
        restartCoinTimer();
    }

    /*
    Adds some coins to the map
    */
    public void initCoins(){
        for(int i = 1; i <= startingCoins; i++) {
            addCoinOnRandomLocation();
        }
    }



    //was used for testing
    //x and y are ints for the tilemap[][]
    public void addCoin(int x, int y){
        Tile tile = map.getTileOnIndex(x, y);
        addCoin(tile);
    }

    public void addCoin(Tile tile) {
        PVector coordinates = map.getTilePixelLocation(tile);
        game.addGameObject(new Coin(game, coordinates));
    }

    public void addCoinOnRandomLocation(){
        Tile tile = map.getRandomEmptyTileWithoutObjects(game.getAllGameObjects());
        if (tile != null){
            addCoin(tile);
        }

    }

    @Override
    public void triggerAlarm(String s) {
        addCoinOnRandomLocation();
        restartCoinTimer();
    }

    public void restartCoinTimer(){
        coinTimer = new Alarm(coinTimerName, coinSpawnInterval);
        coinTimer.addTarget(this);
        coinTimer.start();
    }
}
