package tiles;


import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.EmptyTile;
/**
 * This is an empty tile that serves to mark the spot to spawn the player.
 * It is represented by a 1 in the tile array in Maze.
 */

public class PlayerSpawnTile extends EmptyTile {

    public PlayerSpawnTile(Sprite sprite) {
        super(sprite);
    }

}

