package me.main;


import enemy.Zombie;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.view.View;
import java.util.ArrayList;


public class Game extends GameEngine{
    private ObjectSpawner objectSpawner;

    public static final int WIDTH = 945, HEIGHT = WIDTH / 12 * 9;
    public static final float centerX = ((WIDTH /35) * 17), centerY = ((HEIGHT /35) *15);
    public static final String MEDIA_URL = "src/media/";

    public static void main(String[] args) {
        Game game = new Game();
        game.runSketch();
    }

    @Override
    public void setupGame() {

        addGameObject(new Zombie(this),35,635);

        setView(new View(WIDTH,HEIGHT));
        size(WIDTH,HEIGHT);

        tileMap = new Maze();
        objectSpawner = new ObjectSpawner(this, tileMap);
        //spawns coins and the player
        objectSpawner.spawnStartingObjects();
    }

    @Override
    public void update() {
    }

    /*
    Moved all the setmap() code to Maze.
     */

    public void setView(int width, int height){
        setView(new View(width,height));
        size(width,height);
    }

    /*
    Maybe we should make a Util class?
     */
    public static float clamp(float var, float min, float max){
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else return var;
    }

    /*
    Casts the Vector of all GameObjects into an ArrayList
     */
    public ArrayList<GameObject> getAllGameObjects(){
        ArrayList<GameObject> allGameObjects = new ArrayList<>(getGameObjectItems());
        return allGameObjects;
    }

    public void updateObjectList(GameObject object,float x, float y){
        addGameObject(object,x,y);
    }
}
