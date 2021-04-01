package HUD;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

/**
 *  Class for the players Health.
 *  the healthbar is shown in the Heads Up Display.
 */
public class HealthBar extends GameObject {
    public static float HEALTH = 100;
    private float greenValue = 255;
    private final int WHITE_CEMENT = 200;
    private final int BLACK = 44;

    /**
     * Updates color of the healthbar based on the players health.
     */
    @Override
    public void update() {
        greenValue = clamp(greenValue, 0, 255);

        greenValue = HEALTH * 2;
    }

    /**
     * draws the healthbar
     * @param g PGraphics object used to draw on dashboard
     */
    @Override
    public void draw(PGraphics g) {
        //Black border of healthbar
        g.fill(BLACK);
        g.rect(8, 5, 205, 24);
        //Filling healthbar
        g.fill(WHITE_CEMENT);
        g.rect(10, 7, 201, 20);
        //Color of healthbar
        g.fill(75, (int) greenValue, 0);
        g.rect(10, 7, ((int) HEALTH * 2), 20);
        g.dispose();
    }

    /**
     * keeps value within the boundaries
     * @param var incomming value that needs to be checked
     * @param min minimal value of boundary
     * @param max maximum value of boundary
     * @return returns var within boundraries
     */
    public static float clamp(float var, float min, float max) {
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        } else {
            return var;
        }
    }

    /**
     * changes the amount of health the player has.
     * @param damage the amount gets subtracted from the players's health.
     */
    public static void setHEALTH(float damage) {
        HEALTH = clamp(HEALTH -= damage, 0, 100);
    }

    /**
     * Gets amount of Health the player has
     * @return Health of player
     */
    public static float getHEALTH() {
        return HEALTH;
    }
}