package me.main;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.EmptyTile;
import nl.han.ica.oopg.tile.Tile;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import processing.core.PImage;
import processing.core.PVector;
import tiles.PlayerSpawnTile;
import tiles.WallTile;

import java.util.ArrayList;
import java.util.Random;

public class Maze extends TileMap {
    private final static int mazeTileSize = 35;
    private final static Sprite wallSprite = new Sprite(Game.MEDIA_URL.concat("wallTile.png"));
    private final static TileType<WallTile> wallTileType = new TileType<>(WallTile.class, wallSprite);
    private final static Sprite emptySprite = new Sprite(new PImage(0, 0));
    private final static TileType<PlayerSpawnTile> playerSpawnTileType = new TileType<>(PlayerSpawnTile.class, emptySprite);
    private final static TileType[] mazeTileTypes = {wallTileType, playerSpawnTileType};
    private final static int mazeTilesMap[][] = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0,-1,-1, 0,-1, 0, 0, 0, 0, 0,-1, 0,-1, 0,-1,-1,-1,-1,-1, 0,-1, 0,-1,-1,-1, 0, 0},
            {0,-1, 0, 0,-1,-1,-1,-1,-1, 0,-1, 0,-1,-1,-1, 0,-1, 0,-1,-1,-1, 0, 0, 0,-1, 0, 0},
            {0,-1, 0,-1,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0, 0, 0,-1,-1,-1, 0,-1, 0, 0},
            {0,-1, 0,-1,-1,-1,-1, 0,-1, 0,-1,-1,-1, 0,-1, 0,-1,-1,-1, 0, 0,-1, 0, 0,-1, 0, 0},
            {0,-1, 0,-1, 0, 0, 0, 0,-1,-1,-1, 0, 0, 0, 0, 0,-1, 0,-1, 0,-1,-1,-1, 0,-1,-1, 0},
            {0,-1,-1,-1, 0, 0,-1,-1,-1, 0,-1,-1,-1, 0,-1,-1,-1, 0,-1,-1,-1, 0,-1, 0, 0,-1, 0},
            {0,-1, 0,-1,-1,-1,-1, 0, 0, 0, 0,-1,-1,-1,-1,-1,-1, 0,-1, 0,-1,-1,-1,-1, 0,-1, 0},
            {0,-1, 0, 0, 0, 0,-1, 0,-1,-1,-1,-1, 0, 1, 0,-1, 0, 0,-1, 0,-1, 0,-1, 0, 0,-1, 0},
            {0,-1, 0, 0, 0, 0,-1,-1,-1, 0, 0,-1, 0, 0, 0,-1,-1,-1,-1, 0,-1, 0, 0,-1,-1,-1, 0},
            {0,-1,-1,-1,-1, 0, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0,-1, 0,-1,-1,-1, 0, 0,-1, 0},
            {0,-1, 0, 0,-1, 0,-1,-1,-1, 0,-1,-1, 0,-1,-1, 0,-1, 0,-1,-1,-1, 0,-1, 0,-1,-1, 0},
            {0, 0, 0, 0,-1,-1,-1, 0,-1, 0, 0,-1, 0, 0,-1, 0,-1,-1,-1, 0,-1,-1,-1,-1,-1, 0, 0},
            {0,-1,-1, 0,-1, 0,-1,-1,-1, 0, 0,-1,-1, 0,-1, 0, 0,-1, 0, 0, 0, 0,-1, 0,-1,-1, 0},
            {0,-1,-1,-1,-1, 0,-1, 0,-1, 0, 0, 0,-1, 0,-1,-1, 0,-1,-1, 0,-1, 0,-1, 0, 0,-1, 0},
            {0,-1, 0, 0,-1,-1,-1, 0,-1,-1,-1,-1,-1,-1,-1, 0,-1, 0,-1, 0,-1,-1,-1,-1, 0,-1, 0},
            {0,-1,-1,-1,-1, 0,-1,-1,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0, 0,-1,-1,-1, 0},
            {0, 0, 0,-1, 0, 0,-1, 0,-1,-1,-1, 0,-1, 0,-1,-1,-1,-1,-1, 0,-1, 0,-1,-1, 0,-1, 0},
            {0,-1,-1,-1,-1,-1,-1, 0,-1, 0,-1, 0,-1,-1,-1, 0,-1, 0,-1,-1,-1,-1,-1, 0, 0,-1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    private Tile playerSpawn;
    /*
    I made all these static so I can throw them to the TileMap constructor.
    Not sure if this is the best way.
     */
    public Maze () {
        super(mazeTileSize, mazeTileTypes, mazeTilesMap);
        playerSpawn = findPlayerSpawn();
    }

    /*
    There can be only one playerSpawn, if there's more on the tileMap, it picks the first one.
     */
    private Tile findPlayerSpawn() {
        int[][] tilesIndexes = getTileMap();
        //First, cycle past all the tiles by index with a double for-loop
        for(int y = 0; y < tilesIndexes.length;y++){
            for(int x = 0; x < tilesIndexes[y].length; x++){
                Tile tile = getTileOnIndex(x,y);
                if(tile instanceof PlayerSpawnTile){
                    return tile;
                }
            }
        }
        System.out.println("Kan PlayerSpawn Tile niet vinden. Maze.findPlayerSpawn()");
        return null;
    }

    public PVector getPlayerSpawnLocation(){
        return getTilePixelLocation(playerSpawn);
    }

    /*
    Gets a list of all empty tiles
    Should we run this only once?
    Can more EmptyTiles appear?
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

    /*
    Gets a random tile that is empty and has no GameObjects on it.
    Can still spawn coins under the player's feet for some reason.
     */
    public Tile getRandomEmptyTileWithoutObjects(ArrayList<GameObject> allGameObjects){
        ArrayList<Tile>  listOfEmptyTiles = getEmptyTiles();
        //Now cycle past all GameObjects, check which Tile they're on and remove that one from our list

        for(GameObject object: allGameObjects){
            Tile objectTile = getTileOnPosition((int) object.getX(), (int) object.getY());
            //no check needed because an ArrayList only executes remove() if the object is in the list
            listOfEmptyTiles.remove(objectTile);
        }

        int numberOfEmptyTiles = listOfEmptyTiles.size();
        if (numberOfEmptyTiles <= 0){
            System.out.println("Geen lege tiles om muntje op te spawnen. Maze.getRandomEmptyTileWithoutObjects()");
            return null;
        }
        //Now pick a random Tile from the list
        Random random = new Random();
        int randomNumber = random.nextInt(listOfEmptyTiles.size());
        return listOfEmptyTiles.get(randomNumber);
    }
}
