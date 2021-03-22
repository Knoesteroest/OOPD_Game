package items;

import me.main.Game;
import me.main.Player;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Booster extends Item{
    
    private int effectDuration;
    /*
    added effectDuration to the constructor to force children to set it
     */
    public Booster(Game game, Sprite sprite, int effectDuration) {
        super(game, sprite);
        this.effectDuration=effectDuration;
    }

    @Override
    public void pickUp(Player player){
        //add effect to boostereffect list
        //don't start effect here because it might already be in effect.
    }

    public abstract void startEffect();
    public abstract void endEffect();
}
