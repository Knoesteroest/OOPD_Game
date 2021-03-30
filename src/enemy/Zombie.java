package enemy;

import me.main.Game;
import me.main.Player;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

/**
 * Class for the zombie
 * This enemy tries to follow the player and cannot go through walls.
 */
public class Zombie extends Enemy {

    private float velX,velY; //x,y and speed are already inherited from GameObject
    private static int zombieDamage = 2;

    public Zombie(Game game) {
        super(new Sprite(Game.MEDIA_URL.concat("zombie.gif")), 4, game, zombieDamage);
        setCurrentFrameIndex(1);
        //his.setSpeed(0.85F);
    }

    private int test = 1;
    @Override
    public void update() {
//        x = getX();
//        //x += velX;
//
//        y = getY();
//        //y += velY;
//
//        setX(x);
//        setY(y);

        for (GameObject g: game.getGameObjectItems()){
            if (g instanceof Player){
                GameObject player = g;

                float diffX = x - player.getX();
                float diffY = y - player.getY() - ((player.getHeight() / 2) - (player.getHeight() / 4));
                float distance = (float) Math.sqrt((x-player.getX())*(x - player.getX()) + (y - player.getY()) * (y - player.getY()));

                velX =  ((-1/distance) * diffX );
                velY =  ((-1/distance) * diffY );
                if(test ==1){
                    System.out.println("DIFFERENCE X: " + diffX);
                    System.out.println("DIFFERENCE Y : " +diffY);
                    test =0;
                }
        /**
         * check if X or Y position is closer to player.
         * if X / Y pos != collision with wall
         * set direction to W,A,S,D on closes param
         * sets looking direction to W,A,S,D
         * moves zombie closer to player.
         *
         * OPTION 2
         * GET PLAYER X AND Y
         * GET ZOMBIE X AND Y
         * FIRST SETS X SAME AS PLAYER, THEN SETS Y == PLAYER Y;
         * ||
         * PLAYER.X - ZOMBIE.X les than PLAYER.Y - ZOMBIE.Y
         * ACT ON WHAT VALUE IS LOWEST.
         */





            }
        }
    }
}
