package enemy;

import me.main.Game;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;
import java.util.Random;

/**
 * Class for the zombie Enemy
 */
public class Zombie extends AnimatedSpriteObject implements ICollidableWithTiles {
    private Game game;
    private Random r = new Random();
    private int direction;
    private float speed;
    public Zombie(Game game) {
        super(new Sprite(Game.MEDIA_URL.concat("coin.gif")),4);
        this.game = game;
        setCurrentFrameIndex(1);
        speed = 1.25F;
    }

    @Override
    public void update() {
        direction = r.nextInt(4) ;
       // setCurrentFrameIndex(direction);
        switch (direction){
            case 1:
                 setDirectionSpeed(90, speed);
                 setCurrentFrameIndex(0);
                break;
            case 2:
               // setDirectionSpeed(180, getFriction());
                break;
            case 3:
               // setDirectionSpeed(270, getFriction());
                break;
            case 4:
              //  setDirectionSpeed(0, getFriction());
                break;
        }
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> list) {

        switch (direction){
            case 1:

                break;
            case 2:
                System.out.println(direction);
                break;
            case 3:
                System.out.println(direction);
                break;
            case 4:
                System.out.println(direction);
                break;
        }
    }
}
