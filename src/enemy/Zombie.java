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
    private static float zombieDamage = 0.5f;

    public Zombie(Game game) {
        super(new Sprite(Game.MEDIA_URL.concat("zombie.gif")), 4, game, (int)zombieDamage);
        setCurrentFrameIndex(1);
        //this.setSpeed(0.85F);
    }

    private int test = 1;
    @Override
    public void update() {
//        x = getX();
//        y = getY();

//        x += getxSpeed();
//        y += getySpeed();
//        setX(x);
//        setY(y);

        for (GameObject g: game.getGameObjectItems()){
            if (g instanceof Player){
                GameObject player = g;

                float diffX = this.getCenterX() - player.getCenterX();
                float diffY = this.getCenterY() - player.getCenterY() /*- ((player.getHeight() / 2) - (player.getHeight() / 4))*/;
                float distance = (float) Math.sqrt((this.getCenterX()-player.getCenterX())*(this.getCenterX() - player.getCenterX()) + (this.getCenterY() - player.getCenterY()) * (this.getCenterY() - player.getCenterY()));

                // set speed x/y with speed
                // code hieronder bugt de zombie als distance <35 is
                // add dat afstand in NofTegels komt. /35
                velX =((float) ((-1/distance) * Math.floor(diffX)));
//                if (diffY > 0) diffY += 8;
//                else diffY -= 16;
                velY =((float) ((-1/distance) * Math.floor(diffY)));
                diffX /= 35;
                diffY /= 35;

                if(test ==1){
                    System.out.println("DIFFERENCE X: " + Math.floor(diffX));

                    System.out.println("DIFFERENCE Y : " + Math.floor(diffY));
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
