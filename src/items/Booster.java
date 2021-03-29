package items;

import me.main.Game;
import me.main.ObjectTypeId;
import me.main.Player;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Booster extends Item{
    private double effectDuration;
    public ObjectTypeId typeId;//to-do, make this private
    /*
    added effectDuration to the constructor to force children to set it
     */
    public Booster(Game game, Sprite sprite, double effectDuration, ObjectTypeId typeId) {
        super(game, sprite);
        this.effectDuration = effectDuration;
        this.typeId=typeId;
    }

/*
    add effect to boostereffect list
    don't start effect here because it might already be in effect.
    Item class removes this from the game after this runs
*/
    @Override
    public void pickUp(Player player){
        game.addBoosterEffect(new BoosterEffect(this));
    }

    public double getEffectDuration(){
        return effectDuration;
    }
    public abstract void startEffect();
    public abstract void endEffect();
}
