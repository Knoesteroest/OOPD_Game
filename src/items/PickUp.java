package items;

import me.main.Game;
import me.main.Player;

public abstract class PickUp {
    private Game game;

    PickUp(Game game){
        this.game=game;
    }

    public abstract void pickUp(Player player);

    private void removeThis(){

    }
}
