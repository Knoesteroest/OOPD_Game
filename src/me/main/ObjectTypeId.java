package me.main;

/**
 * This is a list of all objects in our game to reference their type.
 * This is used to check if the type of a booster is already in
 * the list of active booster effects in Game.
 */
public enum ObjectTypeId {
    Player(),
    Zombie(),
    Saw(),
    Coin(),
    Flash(),
    Speed(),
    DoubleScore()
}
