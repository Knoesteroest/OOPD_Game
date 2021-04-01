package items;

/**
 * This is the super-class of all boosters. When a booster is picked up by the player it is passed
 * to a BoosterEffect and then removed from game. This does not destroy the Booster object,
 * because the BoosterEffect still has a reference. The BoosterEffect is then added to the list
 * kept by Game.
 * The methods startEffect() and endEffect() are called from Game.
 */

import me.main.Game;
import me.main.Player;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Booster extends Item {
    private double effectDuration;
    private BoosterId typeId;

    /*
    added effectDuration to the constructor to force children to set it
     */
    public Booster(Game game, Sprite sprite, double effectDuration, BoosterId typeId) {
        super(game, sprite);
        this.effectDuration = effectDuration;
        this.typeId = typeId;
    }

    /*
        This adds an effect to boostereffect list
        Item class removes this from the game after this runs
    */
    @Override
    public void pickUp(Player player) {game.addBoosterEffect(new BoosterEffect(this));}

    public double getEffectDuration() {return effectDuration;}

    public abstract void startEffect();

    public abstract void endEffect();

    public BoosterId getTypeId() {return typeId;}
}
