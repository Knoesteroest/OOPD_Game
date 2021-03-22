package enemy;

import me.main.Game;
import me.main.Player;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

/**
 * Class for the zombie Enemy
 */
public class Zombie extends Enemy {
    private float speed;
    private Game game;
    private float velX,velY,x,y;

    public Zombie(Game game) {
        super(new Sprite(Game.MEDIA_URL.concat("zombie.gif")), 4,game);
        setCurrentFrameIndex(1);
        this.game = game;
        this.speed = 0.85F;
    }


    @Override
    public void update() {
        x = getX();
        x += velX;

        y = getY();
        y += velY;

        setX(x);
        setY(y);

        for (GameObject g: game.getGameObjectItems()){
            if (g instanceof Player){
                GameObject player = g;

        float diffX = x - player.getX();
        float diffY = y - player.getY() - ((player.getHeight() /2) - (player.getHeight() /4));
        float distance = (float)Math.sqrt((x-player.getX())*(x - player.getX()) + (y - player.getY())*(y - player.getY()));

        velX =  ((-1/distance) * diffX );
        velY =  ((-1/distance) * diffY );
            }
        }

    }
}
