package items;

import me.main.Game;
import me.main.Player;
import nl.han.ica.oopg.objects.Sprite;
/**
 * This is the super-class of all boosters. When a booster is picked up by the player it is passed
 * to a BoosterEffect and then removed from game. This does not destroy the Booster object,
 * because the BoosterEffect still has a reference. The BoosterEffect is then added to the list
 * kept by Game.
 * The methods startEffect() and endEffect() are called from Game.
 */

public abstract class Booster extends Item {
    private double effectDuration;
    private BoosterId typeId;

    /**
    Constructor
     @param effectDuration The number of seconds the effect of this booster should last.
     @param game The game object to add the BoosterEffect to.
     @param sprite The sprite for this Booster.
     @param typeId The BoosterId for this Booster.
     */
    public Booster(Game game, Sprite sprite, double effectDuration, BoosterId typeId) {
        super(game, sprite);
        this.effectDuration = effectDuration;
        this.typeId = typeId;
    }

    /**
        This adds an effect to boostereffect list in Game
        This is called by Player when picked up.
        The Item class removes this Booster from the game after this runs.
     @param player The player object
    */
    @Override
    public void pickUp(Player player) {game.addBoosterEffect(new BoosterEffect(this));}
    /**
    Gets the time that this booster lasts.
     @return The duration of this booster in seconds.
     */
    public double getEffectDuration() {return effectDuration;}

    /**
     * This method is run when the booster is picked up.
     */
    public abstract void startEffect();

    /**
     * This method is run when the duration runs out.
     */
    public abstract void endEffect();

    /**
     * Gets the type of this booster.
     * @return The type of this booster.
     */
    public BoosterId getTypeId() {return typeId;}
}
