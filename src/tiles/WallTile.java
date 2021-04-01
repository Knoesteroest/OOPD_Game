package tiles;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;

/**
 * This is the wall tile that is impenetrable to players and enemies.
 * They are represented by 0's in the tile array in Maze.
 */

public class WallTile extends Tile {
    public WallTile(Sprite sprite) {
        super(sprite);
    }
}
