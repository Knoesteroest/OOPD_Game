package me.main;

/**
 * This class creates all game objects and spawns them in the map.
 * It is called at game start to spawn the initial objects and by
 * Difficulty when new enemies get added when difficulty increases.
 * It runs a timer to spawn more Coin objects at regular intervals.
 */

import enemy.CircularSaw;
import enemy.Zombie;
import items.Coin;
import items.FlashBomb;
import items.SpeedBoost;
import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.tile.Tile;
import nl.han.ica.oopg.tile.TileMap;
import processing.core.PVector;
import tiles.PlayerSpawnTile;

public class ObjectSpawner implements IAlarmListener {
    private Maze map;
    private Game game;
    private Alarm coinTimer;
    private final String coinTimerName = "coinTimer";
    private final double coinSpawnInterval = 5;
    private final int startingCoins = 5;
    private final int maxCoins = 10;
    //private Difficulty difficulty;

    public ObjectSpawner(Game game, TileMap map){
        this.game = game;
        this.map = (Maze) map;
        restartCoinTimer();
    }
    /*
    Needs the difficulty object to hand to the spawned Player object
     */
    public void spawnStartingObjects(Difficulty difficulty) {
        spawnPlayer(difficulty);
        spawnInitialCoins();
        spawnZombie();
        spawnSaw(0);
        spawnTestObjects();
    }

    public void spawnTestObjects() {
        spawnObject(new Zombie(game), map.getTileOnIndex(8,2)); //new spawn method by index
        spawnObject(new SpeedBoost(game), map.getTileOnIndex(11,18));
        spawnObject(new SpeedBoost(game), map.getTileOnIndex(17,4));
        spawnObject(new FlashBomb(game), map.getTileOnIndex(1,1));
        spawnObject(new FlashBomb(game), map.getTileOnIndex(25,18));
        spawnSaw(1);
        spawnSaw(2);
    }

    public void spawnObject(GameObject object, Tile tile){
        PVector coordinates = map.getTilePixelLocation(tile);
        game.addGameObject(object, coordinates.x, coordinates.y);
    }
    /*
    Puts the player on the PlayerSpawnTile
     */
    private void spawnPlayer(Difficulty difficulty){
        PlayerSpawnTile playerSpawnTile = map.getPlayerSpawnTile();
        spawnObject(new Player(game, difficulty, playerSpawnTile), playerSpawnTile);
    }

    public void spawnSaw(int spawnTilenr){
        Tile tile = map.getSawSpawnTiles().get(spawnTilenr);
        spawnObject(new CircularSaw(game), tile);
    }

    public void spawnZombie(){
        Tile tile = map.getSuitableSpawnTile(game.getAllGameObjects());
        if (tile != null){
            spawnObject(new Zombie(game), tile);
        }
    }
    /*
    Adds some coins to the map
    */
    private void spawnInitialCoins(){
        for(int i = 1; i <= startingCoins; i++) {
            addCoin();
        }
    }

    /* spawns a coin on a random tile*/
    private void addCoin(){
        Tile tile = map.getSuitableSpawnTile(game.getAllGameObjects());
        if (tile != null){
            spawnObject(new Coin(game), tile);
        }
    }

    @Override
    public void triggerAlarm(String s) {
        if (game.countCoins() < maxCoins){
            addCoin();
        }
        restartCoinTimer();
    }

    private void restartCoinTimer(){
        coinTimer = new Alarm(coinTimerName, coinSpawnInterval);
        coinTimer.addTarget(this);
        coinTimer.start();
    }
}
