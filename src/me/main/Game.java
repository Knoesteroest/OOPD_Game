package me.main;


import enemy.Enemy;
import enemy.Zombie;
import items.BoosterEffect;
import items.Coin;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.view.View;
import java.util.ArrayList;


public class Game extends GameEngine{
    private ObjectSpawner objectSpawner;
    private ArrayList<BoosterEffect> activeBoosterEffects;

    public static final int WIDTH = 945, HEIGHT = WIDTH / 12 * 9;
    public static final float centerX = ((WIDTH /35) * 17), centerY = ((HEIGHT /35) *15);
    public static final String MEDIA_URL = "src/media/";

    private Difficulty difficulty;

    public static void main(String[] args) {
        Game game = new Game();
        game.runSketch();
    }

    @Override
    public void setupGame() {

        setView(new View(WIDTH,HEIGHT));
        size(WIDTH,HEIGHT);

        tileMap = new Maze();

        objectSpawner = new ObjectSpawner(this, tileMap);
        difficulty = new Difficulty(objectSpawner, StartingDifficulty.EASY.getDifficultyLevel());
        activeBoosterEffects = new ArrayList<>();
        //spawns coins and the player
        objectSpawner.spawnStartingObjects(difficulty);
    }

    @Override
    public void update() {
    }

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
        return new ArrayList<>(getGameObjectItems());
    }

//BoosterEffects code:

    /*
    adds booster effect to the list and starts the effect if there wasn't one in the list yet
     */
    public void addBoosterEffect(BoosterEffect boosterEffect){
        if (!isBooster(boosterEffect.booster.typeId)) {
            boosterEffect.booster.startEffect();
        }
        activeBoosterEffects.add(boosterEffect);
    }

    /*
    checks if the booster type is already in the list
     */
    private boolean isBooster(ObjectTypeId typeId){
        for(BoosterEffect effect: activeBoosterEffects){
            if(effect.booster.typeId==typeId){
                return true;
            }
        }
        return false;
    }

    public void removeBoosterEffect(BoosterEffect boosterEffect){
        activeBoosterEffects.remove(boosterEffect);
        if (!isBooster(boosterEffect.booster.typeId)){
            boosterEffect.booster.endEffect();
        }
    }

    /*
    Could be a variable set once instead, to-do...
     */
    public Player getPlayer(){
        for (GameObject o: getAllGameObjects()){
            if (o instanceof Player){
                return (Player) o;
            }
        }
        return null;
    }

    /*
    Nodig voor de FlashBoost
     */
    public  ArrayList<Enemy> getAllEnemies(){
        ArrayList<Enemy> allEnemies = new ArrayList<>();
        for (GameObject o: getAllGameObjects()){
            if (o instanceof Enemy){
                allEnemies.add((Enemy)o);
            }
        }
        return allEnemies;
    }

    public int countCoins(){
        int coinCount = 0;
        for(GameObject o: getAllGameObjects()){
            if (o instanceof Coin){
                coinCount++;
            }
        }
        return coinCount;
    }
}
