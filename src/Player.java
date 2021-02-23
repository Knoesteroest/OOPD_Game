import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.data.IntList;
import processing.core.PConstants;


public class Player extends SpriteObject {

    private int kleur = 0xFF0000FF;
    private IntList pressedKeys = new IntList();

    public Player(float x, float y) {
        super(new Sprite("sprites/dude.png"));
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        if (pressedKeys.size()>0) {
            //System.out.println(pressedKeys);
            int lastKey = pressedKeys.get(pressedKeys.maxIndex());
            switch (lastKey) {
                case UP:
                    setDirectionSpeed(0, 2);
                    break;
                case DOWN:
                    setDirectionSpeed(180, 2);
                    break;
                case LEFT:
                    setDirectionSpeed(270, 2);
                    break;
                case RIGHT:
                    setDirectionSpeed(90, 2);
                    break;
            }
        }else{
            setSpeed(0);
        }
    }
/*
    @Override
    public void draw(PGraphics pGraphics) {
        pGraphics.fill(kleur);
        pGraphics.rectMode(CENTER);
        pGraphics.rect(x, y, width, height);
    }
*/
    @Override
    public void keyPressed(int keyCode, char key) {
        /*if (keyCode==32){
            setDirection((float) (Math.random()*360));
        }
*/
        switch(keyCode){
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
                pressedKeys.append(keyCode); //appendUnique bestaat niet?
        }

        /*
        {UP, DOWN, LEFT, RIGHT}
        {87, 83, 65, 68} WSAD
         */
    }

    @Override
    public void keyReleased(int keyCode, char key) {
        switch(keyCode){
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
                pressedKeys.removeValues(keyCode);
        }
    }

    public void setKleur(int kleur) {
        this.kleur = kleur;
    }
}
