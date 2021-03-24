package enemy;

import me.main.Game;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.EmptyTile;
import nl.han.ica.oopg.tile.Tile;
import processing.core.PVector;
import tiles.WallTile;

import static processing.core.PApplet.*;

public class CircularSaw extends Enemy{
    private final static Sprite sprite = new Sprite(Game.MEDIA_URL.concat("sawAnim.png"));

    public CircularSaw(Game game, PVector coordinates) {
        super(sprite, 3, game, coordinates);
        setCurrentFrameIndex(0);
        this.setSpeed(1);
        setDirection(0);
//        test();
//        System.exit(5);
    }

    public void turnLeft(){
        setDirection((getDirection() - 90) % 360);
//        System.out.println("Turning left.");
    }
    public void turnRight(){
        setDirection((getDirection() + 90) % 360);
//        System.out.println("Turning right.");
    }

    @Override
    public void update() {
        if (isMiddleOfTile()){
//            System.out.println("middle");
            if (getLeftTile() instanceof EmptyTile) {
                turnLeft();
            } else if (getFrontTile() instanceof WallTile) {
                turnRight();
            }
        }
        nextFrame();
    }

    public void test(){
        Tile testTile = map.getTileOnIndex(5,5);
        Tile resultTile = getAdjacentTile(testTile, 0, 0);
        PVector index = map.getTileIndex(resultTile);
        System.out.println("the tile in front of 5,5 going up is " + index.x +":"+ index.y);

        resultTile = getAdjacentTile(testTile, 0, -90);
        index = map.getTileIndex(resultTile);
        System.out.println("the tile in left of 5,5 going up is " + index.x +":"+ index.y);

        resultTile = getAdjacentTile(testTile, 270, -90);
        index = map.getTileIndex(resultTile);
        System.out.println("the tile in left of 5,5 going left is " + index.x +":"+ index.y);

        resultTile = getAdjacentTile(testTile, 180, -90);
        index = map.getTileIndex(resultTile);
        System.out.println("the tile in left of 5,5 going down is " + index.x +":"+ index.y);
    }

    /*
    tile: the tile you're on
    heading: the direction you're going in
    side: the direction relative to your heading you want the tile from
     */
    private Tile getAdjacentTile(Tile tile, float heading, float side) {
        PVector index = map.getTileIndex(tile);
        index.x += sin(radians(heading + side));
//        System.out.println(PApplet.sin(heading + side));
//        System.out.println(PApplet.sin(180));
//        System.out.println(index.x);
        index.y -= cos(radians(heading + side));
//        System.out.println(index.y);
        return map.getTileOnIndex((int)index.x, (int) index.y);
    }

    private Tile getFrontTile() {
        Tile tile = getAdjacentTile(getTile(), getDirection(), 0);
        PVector index = map.getTileIndex(getTile());
//        System.out.println("frontTile: " + index.x +":"+ index.y);
        return tile;
    }

    private Tile getLeftTile() {
        Tile tile = getAdjacentTile(getTile(), getDirection(), -90);
        PVector index = map.getTileIndex(getTile());
//        System.out.println("leftTile: " + index.x +":"+ index.y);
        return tile;
    }
    /*
    get the tile this is on
     */
    private Tile getTile(){
        Tile tile = map.getTileOnPosition((int) getCenterX(), (int) getCenterY());
//        System.out.println("ourTile: " + map.getTileIndex(tile).x +":"+ map.getTileIndex(tile).y);
        return tile;
    }

    private boolean isMiddleOfTile(){
        PVector tileCoordinates = map.getTilePixelLocation(getTile());
        return (PVector.dist(new PVector(getX(), getY()), tileCoordinates) < 1.0); //close enough?
    }
}
