package me.main;

/**
 * This is our TileMap; It generates our only level and gives map-related information
 * to other objects.
 *
 * Created in Game and passed to all objects that need it.
 *
 * @author Pieter Oosterbroek & Martijn Engels
 *
 */

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.EmptyTile;
import nl.han.ica.oopg.tile.Tile;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import processing.core.PImage;
import processing.core.PVector;
import tiles.PlayerSpawnTile;
import tiles.SawSpawnTile;
import tiles.WallTile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Maze extends TileMap {
    /**
     * The size of our tiles in pixels
     */
    private final static int mazeTileSize = 35;
    private final static Sprite wallSprite = new Sprite(Game.MEDIA_URL.concat("wallTile.png"));
    private final static TileType<WallTile> wallTileType = new TileType<>(WallTile.class, wallSprite);
    private final static Sprite emptySprite = new Sprite(new PImage(0, 0));
    private final static TileType<PlayerSpawnTile> playerSpawnTileType = new TileType<>(PlayerSpawnTile.class, emptySprite);
    private final static TileType<SawSpawnTile> sawSpawnTileType = new TileType<>(SawSpawnTile.class, emptySprite);
    private final static TileType[] mazeTileTypes = {wallTileType, playerSpawnTileType, sawSpawnTileType};
    /**
     * The array that is used to generate our level.
     * -1 is a path
     * 0 is a wall
     * 1 is the player spawn
     * 2 is a saw spawn
     */
    private final static int mazeTilesMap[][] = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0,-1,-1,-1,-1, 0, -1, -1, -1, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,-1,-1,-1,-1,-1, 0, 0},
            {0,-1, 0, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0, 0, 0,-1,-1,-1, 0,-1, 0, 0},
            {0,-1, 0,-1,-1,-1,-1, 0,-1, 0,-1,-1,-1, 0,-1,-1,-1,-1,-1, 0, 0, 0,-1, 0,-1, 0, 0},
            {0,-1, 0,-1, 0, 0, 0, 0,-1,-1,-1, 0, 0, 0, 0, 0,-1, 0,-1, 0,-1,-1,-1, 0,-1,-1, 0},
            {0,-1, 0,-1, 0, 0,-1,-1,-1, 0,-1,-1, 0, 0, 0,-1,-1, 0,-1,-1,-1, 0,-1, 0, 0,-1, 0},
            {0,-1, 0,-1,-1,-1,-1, 0, 0, 0, 0,-1,-1,-1,-1,-1, 0, 0,-1, 0,-1, 0,-1,-1,-1,-1, 0},
            {0,-1,-1,-1, 0, 0,-1, 0,-1,-1,-1,-1, 0, 1, 0,-1, 0, 0,-1, 0,-1, 0, 0, 0, 0, 2, 0},
            {0,-1, 0, 0, 0, 0,-1,-1,-1, 0, 0,-1, 0, 0, 0,-1,-1,-1,-1, 0,-1, 0,-1,-1,-1,-1, 0},
            {0,-1,-1,-1,-1, 0, 0, 0, 2,-1,-1,-1,-1,-1,-1,-1,-1, 0,-1, 0,-1,-1,-1, 0, 0,-1, 0},
            {0,-1, 0, 0,-1, 0,-1,-1,-1, 0,-1,-1, 0, 0,-1, 0, 0, 0,-1,-1,-1, 0,-1, 0,-1,-1, 0},
            {0, 0, 0, 0,-1,-1,-1, 0,-1, 0, 0,-1,-1, 0,-1, 0,-1,-1,-1, 0,-1,-1,-1,-1,-1, 0, 0},
            {0, 0,-1, 0,-1, 0,-1,-1,-1,-1,-1, 0,-1, 0,-1, 0,-1, 0, 0, 0, 0, 0,-1, 0,-1,-1, 0},
            {0,-1,-1,-1,-1, 0,-1, 0, 0, 0,-1, 0,-1, 0,-1,-1,-1, 0,-1,-1,-1, 0,-1, 0, 0,-1, 0},
            {0,-1, 0, 0,-1,-1,-1, 0,-1, 0,-1,-1,-1,-1,-1, 0,-1, 0,-1, 0,-1,-1,-1,-1, 0,-1, 0},
            {0,-1,-1,-1,-1, 0,-1,-1,-1, 0,-1, 0, 0, 0, 0, 0, 2, 0,-1, 0,-1, 0, 0,-1,-1,-1, 0},
            {0,-1, 0, 0, 0, 0,-1, 0,-1, 0,-1, 0,-1,-1,-1, 0,-1, 0,-1, 0,-1, 0,-1,-1, 0,-1, 0},
            {0,-1,-1,-1,-1,-1,-1, 0,-1,-1,-1,-1,-1, 0,-1,-1,-1,-1,-1, 0,-1,-1,-1, 0, 0,-1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    private PlayerSpawnTile playerSpawn;
    private ArrayList<SawSpawnTile> sawSpawnTiles;

    public Maze () {
        super(mazeTileSize, mazeTileTypes, mazeTilesMap);
        playerSpawn = findPlayerSpawn();
        sawSpawnTiles = findSawSpawns();
    }

    private ArrayList<SawSpawnTile> findSawSpawns(){
        ArrayList<SawSpawnTile> sawSpawnList = new ArrayList<>();
        int[][] tileIndexes = getTileMap();
        //First, cycle past all the tiles by index with a double for-loop
        for(int y = 0; y < tileIndexes.length;y++){
            for(int x = 0; x < tileIndexes[y].length; x++){
                Tile tile = getTileOnIndex(x,y);
                if(tile instanceof SawSpawnTile){
                    sawSpawnList.add((SawSpawnTile) tile);
                }
            }
        }
        if(sawSpawnList.size() == 0) {
            System.out.println("Kan geen SawSpawnTiles vinden. Maze.findSawSpawns()");
            return null;
        } else{
            Collections.reverse(sawSpawnList); //reversed because I like the right-most spawn better
            return sawSpawnList;
        }
    }

    /**
    *Finds the player spawn tile in the tileMap array and returns it
     *There can be only one playerSpawn, if there's more on the tileMap, it picks the first one.
     */
    private PlayerSpawnTile findPlayerSpawn() {
        int[][] tileIndexes = getTileMap();
        //First, cycle past all the tiles by index with a double for-loop
        for(int y = 0; y < tileIndexes.length;y++){
            for(int x = 0; x < tileIndexes[y].length; x++){
                Tile tile = getTileOnIndex(x,y);
                if(tile instanceof PlayerSpawnTile){
                    return (PlayerSpawnTile) tile;
                }
            }
        }
        System.out.println("Fout: Kan PlayerSpawn Tile niet vinden. Maze.findPlayerSpawn()");
        return null;
    }

    public PlayerSpawnTile getPlayerSpawnTile() {return playerSpawn;}
    public ArrayList<SawSpawnTile> getSawSpawnTiles() {
        return sawSpawnTiles;
    }

    /**
    Gets a list of all empty tiles on the tileMap
     */
    public ArrayList<Tile> getEmptyTiles(){
        ArrayList<Tile> listOfEmptyTiles = new ArrayList<>();
        int[][] tilesIndexes = getTileMap();
        //First, cycle past all the tiles by index with a double for-loop
        for(int y = 0; y < tilesIndexes.length;y++){
            for(int x = 0; x < tilesIndexes[y].length; x++){
                Tile tile = getTileOnIndex(x,y);
                if(tile instanceof EmptyTile){
                    listOfEmptyTiles.add(tile);
                }
            }
        }
        return listOfEmptyTiles;
    }

    /**
    *Gets a random tile that is empty and has no GameObjects on it.
     * Used for spawning different objects.
     *@param allGameObjects A list of all game objects that disqualify a tile.
     */
    //TODO bug: This can still spawn coins under the player's feet.
    public Tile getSuitableSpawnTile(ArrayList<GameObject> allGameObjects){
        ArrayList<Tile> listOfEmptyTiles = getEmptyTiles();
        //Cycle past all GameObjects, check which Tile they're on and remove that one from our list

        for(GameObject object: allGameObjects){
            Tile objectTile = getTileOnPosition((int) object.getCenterX(), (int) object.getCenterY());
            //no check needed because an ArrayList only executes remove() if the object is in the list
            listOfEmptyTiles.remove(objectTile);
        }

        int numberOfEmptyTiles = listOfEmptyTiles.size();
        if (numberOfEmptyTiles <= 0){
            System.out.println("Geen lege tiles om voorwerp op te spawnen. Maze.getSuitableSpawnTile()");
            return null;
        }
        //Pick a random Tile from the list
        Random random = new Random();
        int randomNumber = random.nextInt(listOfEmptyTiles.size());
        return listOfEmptyTiles.get(randomNumber);
    }

    /**
     * Replaces the PlayerSpawn tile with a Wall tile.
     * Used to close the player spawn after the player leaves.
     */
    public void closeSpawn(){
        PVector coordinates = getTileIndex(playerSpawn);
        setTile((int) coordinates.x, (int) coordinates.y, 0);
        playerSpawn = null;
    }
}
