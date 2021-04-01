package me.main;


import HUD.HUD;
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

/**
 * This class creates all game objects and spawns them in the map.
 * It is called at game start to spawn the initial objects and by
 * Difficulty when new enemies get added when difficulty increases.
 * It runs a timer to spawn more Coin objects at regular intervals.
 */
public class ObjectSpawner implements IAlarmListener {

    private Maze map;
    private Game game;
    private Alarm coinTimer;
    private HUD hud;

    private final String coinTimerName = "coinTimer";
    private final double coinSpawnInterval = 5;
    private final int startingCoins = 5;
    private final int maxCoins = 10;

    /**
     * Creates a new spawner object.
     * @param game The main program
     * @param map the Tilemap that has been initialized
     * @param hud the dashboard
     */
    public ObjectSpawner(Game game, TileMap map, HUD hud) {
        this.game = game;
        this.map = (Maze) map;
        restartCoinTimer();
        this.hud = hud;
    }

    /**
     * Spawns the objects that need to exist at the start of the game:
     * Player, coins, 1 saw and some boosters.
     * Needs the difficulty object to hand to the spawned Player object.
     */
    public void spawnStartingObjects(Difficulty difficulty) {
        spawnPlayer(difficulty);
        spawnInitialCoins();
        spawnInitialBoosters();
        spawnSaw(0);
    }

    /**
     * Creates the booster objects to spawn in the map
     */
    public void spawnInitialBoosters() {
        spawnObject(new SpeedBoost(game, hud));
        spawnObject(new SpeedBoost(game, hud));
        spawnObject(new SpeedBoost(game, hud));
        spawnObject(new FlashBomb(game, hud));
        spawnObject(new FlashBomb(game, hud));
        spawnObject(new FlashBomb(game, hud));
    }

    /**
     * function to call when you want editional objects on the playingfield.
     * You need to add the objects in the body, by default empty
     */
    public void spawnTestObjects() {
        // place objects to test here
    }

    /**
     * Spawns an object on a random empty tile.
     *
     * @param object The game object to spawn.
     */
    public void spawnObject(GameObject object) {
        Tile tile = map.getSuitableSpawnTile(game.getAllGameObjects());
        spawnObject(object, tile);
    }

    /**
     * Spawns an object on a specified tile.
     *
     * @param object The game object to spawn.
     * @param tile   The tile to spawn it on.
     */
    public void spawnObject(GameObject object, Tile tile) {
        PVector coordinates = map.getTilePixelLocation(tile);
        coordinates.x += map.getTileSize() / 2;
        coordinates.y += map.getTileSize() / 2;

        game.addGameObject(object, coordinates.x, coordinates.y);
        object.setX(coordinates.x - object.getWidth() / 2);
        object.setY(coordinates.y - object.getHeight() / 2);
    }

    /**
     * Spawns the player on the PlayerSpawnTile
     */
    private void spawnPlayer(Difficulty difficulty) {
        PlayerSpawnTile playerSpawnTile = map.getPlayerSpawnTile();
        spawnObject(new Player(game, difficulty, playerSpawnTile, hud), playerSpawnTile);
    }

    /**
     * Spawn the CircularSaw on the playingfield
     *
     * @param spawnTilenr the assigned place for the CircularSaw to spawn.
     */
    public void spawnSaw(int spawnTilenr) {
        Tile tile = map.getSawSpawnTiles().get(spawnTilenr);
        if (tile != null) {
            spawnObject(new CircularSaw(game), tile);
        }
    }

    /**
     * Spawns the Zomnie on the playingfield.
     */
    public void spawnZombie() {
        Tile tile = map.getSuitableSpawnTile(game.getAllGameObjects());
        if (tile != null) {
            spawnObject(new Zombie(game), tile);
        }
    }

    /**
     * Adds a number of coins to the map according to the startingCoins variable
     */
    private void spawnInitialCoins() {
        for (int i = 1; i <= startingCoins; i++) {
            addCoin();
        }
    }

    /**
     * Spawns a coin on a random tile.
     */
    private void addCoin() {
        Tile tile = map.getSuitableSpawnTile(game.getAllGameObjects());
        if (tile != null) {
            spawnObject(new Coin(game), tile);
        }
    }

    /**
     * Implementation from the Alarm interface.
     * When the Alarm triggers, a coin is added and the alarm is restarted.
     *
     * @param s Not used, but mandatory. Any string will do.
     */
    @Override
    public void triggerAlarm(String s) {
        if (game.countCoins() < maxCoins) {
            addCoin();
        }
        restartCoinTimer();
    }

    /**
     * Sets spawning time of the coin back to 10 seconds
     */
    private void restartCoinTimer() {
        coinTimer = new Alarm(coinTimerName, coinSpawnInterval);
        coinTimer.addTarget(this);
        coinTimer.start();
    }
}
