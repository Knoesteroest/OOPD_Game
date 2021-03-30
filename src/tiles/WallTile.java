package tiles;

/**
 * This is the wall tile that is impenetrable to players and enemies.
 * They are represented by 0's in the tile array in Maze.
 */

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;

public class WallTile extends Tile {
    public WallTile(Sprite sprite) {
        super(sprite);
    }
}
