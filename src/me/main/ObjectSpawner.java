package me.main;

import enemy.Zombie;
import items.Coin;
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
        spawnStartingCoins();
        spawnZombie();
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
    private void spawnStartingCoins(){
        for(int i = 1; i <= startingCoins; i++) {
            addCoinOnRandomTile();
        }
    }

    //was used for testing
    //x and y are ints for the tilemap[][]
    public void addCoin(int x, int y){
        Tile tile = map.getTileOnIndex(x, y);
        addCoin(tile);
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
        addCoinOnRandomTile();
        restartCoinTimer();
    }

    private void restartCoinTimer(){
        coinTimer = new Alarm(coinTimerName, coinSpawnInterval);
        coinTimer.addTarget(this);
        coinTimer.start();
    }

}
