package tiles;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.EmptyTile;

/**
 * This is an empty tile that servers to mark the spots to spawn new circular saws.
 * They are represented by 2's in the tile array in Maze.
 */
public class SawSpawnTile extends EmptyTile {

    public SawSpawnTile(Sprite sprite) {
        super(sprite);
    }

}

