package me.main;

import nl.han.ica.oopg.objects.GameObject;

import java.util.LinkedList;

public class Handler{
    public LinkedList<GameObject> objects = new LinkedList<>();
    private Game game;

    public void addObject(GameObject object, float x, float y,Game game){
        this.game = game;

        this.objects.add(object);
        game.updateObjectList(objects.getLast(), x, y);
    }
}
