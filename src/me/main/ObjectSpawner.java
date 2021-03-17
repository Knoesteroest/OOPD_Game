package me.main;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.tile.EmptyTile;
import nl.han.ica.oopg.tile.Tile;
import nl.han.ica.oopg.tile.TileMap;
import processing.core.PVector;
import java.util.ArrayList;
import java.util.Random;

public class ObjectSpawner implements IAlarmListener {
    private TileMap map;
    private Game game;
    private Alarm coinTimer;
    private final String coinTimerName = "coinTimer";
    private final double coinSpawnInterval = 5;
    private final int startingCoins = 10;

    public ObjectSpawner(Game game, TileMap map){
        this.game = game;
        this.map = map;
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

    /*
    Gets a random tile that is empty and has no GameObjects on it.
    Moet in de Maze class met andere Tile gerelateerde functies
     */
    public Tile getRandomEmptyTile(){
        ArrayList<Tile> listOfEmptyTiles = new ArrayList<>();
        int[][] tilesIndexes = map.getTileMap();
        //First, cycle past all the tiles and make a list of empty tiles
        for(int y = 0; y < tilesIndexes.length;y++){
            for(int x = 0; x < tilesIndexes[y].length; x++){
                Tile tile = map.getTileOnIndex(x,y);
                if(tile instanceof EmptyTile){
                    listOfEmptyTiles.add(tile);
                }
            }
        }
        //Now cycle past all GameObjects, check which Tile they're on and remove that one from our list

        ArrayList<GameObject> allGameObjects = new ArrayList<>(game.getGameObjectItems());
        for(GameObject object: allGameObjects){

            Tile objectTile = map.getTileOnPosition((int) object.getX(), (int) object.getY());
            //geen check nodig omdat remove() al optioneel is bij een ArrayList
                listOfEmptyTiles.remove(objectTile);

        }
        int numberOfEmptyTiles = listOfEmptyTiles.size();
        if (numberOfEmptyTiles <= 0){
            System.out.println("Geen lege tiles om muntje op te spawnen.");
            return null;
        }
        //Now pick a random Tile from the list
        Random random = new Random();
        int randomNumber = random.nextInt(listOfEmptyTiles.size());
        return listOfEmptyTiles.get(randomNumber);
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
        Tile tile = getRandomEmptyTile();
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
