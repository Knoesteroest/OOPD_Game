package HUD;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class HealthBar extends GameObject {
    public static float HEALTH = 100;
    private float greenValue = 255;
    private final int WHITE_CEMENT = 200;
    private final int BLACK = 44;

    @Override
    public void update() {
        greenValue = clamp(greenValue, 0, 255);

        greenValue = HEALTH * 2;
        if (HEALTH == 0) {
            //TODO END SCREEN
            System.exit(1);
        }
    }

    @Override
    public void draw(PGraphics g) {
        g.fill(BLACK);
        g.rect(8, 5, 205, 24);
        g.fill(WHITE_CEMENT);
        g.rect(10, 7, 201, 20);
        g.fill(75, (int) greenValue, 0);
        g.rect(10, 7, ((int) HEALTH *2), 20);
        g.dispose();
    }


    public static float clamp(float var, float min, float max) {
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        } else {
            return var;
        }
    }

    public static void setHEALTH(float damage) {
        HEALTH = clamp(HEALTH-= damage, 0, 100);
    }
}