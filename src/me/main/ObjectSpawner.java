package me.main;

import enemy.CircularSaw;
import enemy.Zombie;
import items.Coin;
import items.SpeedBoost;
import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.tile.Tile;
import nl.han.ica.oopg.tile.TileMap;
import processing.core.PVector;

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
        game.addGameObject(new SpeedBoost(game), 35,635);//test Booster
        game.addGameObject(new SpeedBoost(game), 635,35);//test Booster
        game.addGameObject(new CircularSaw(game, new PVector(385,280)));
    }
    /*
    Puts the player on the PlayerSpawnTile
     */
    private void spawnPlayer(Difficulty difficulty){
        PVector playerSpawn = map.getPlayerSpawnLocation();
        game.addGameObject(new Player(game, difficulty), playerSpawn.x, playerSpawn.y);
    }

    public void spawnEnemy(ObjectTypeId enemyType) {
        if(enemyType == ObjectTypeId.Zombie){
            spawnZombie();
        } else if (enemyType == ObjectTypeId.Saw){
            //spawnSaw();
        }
    }

    public void spawnZombie(){
        Tile tile = map.getRandomEmptyTileWithoutObjects(game.getAllGameObjects());
        if (tile != null){
            PVector coordinates = map.getTilePixelLocation(tile);
            game.addGameObject(new Zombie(game, coordinates));
        }
    }
    /*
    Adds some coins to the map
    */
    private void spawnInitialCoins(){
        for(int i = 1; i <= startingCoins; i++) {
            addCoinOnRandomTile();
        }
    }

    private void addCoin(Tile tile) {
        PVector coordinates = map.getTilePixelLocation(tile);
        game.addGameObject(new Coin(game, coordinates));
    }

    private void addCoinOnRandomTile(){
        Tile tile = map.getRandomEmptyTileWithoutObjects(game.getAllGameObjects());
        if (tile != null){
            addCoin(tile);
        }
    }

    @Override
    public void triggerAlarm(String s) {
        if (game.countCoins() < maxCoins){
            addCoinOnRandomTile();
        }
        restartCoinTimer();
    }

    private void restartCoinTimer(){
        coinTimer = new Alarm(coinTimerName, coinSpawnInterval);
        coinTimer.addTarget(this);
        coinTimer.start();
    }
}
