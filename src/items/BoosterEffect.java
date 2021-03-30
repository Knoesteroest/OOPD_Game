package items;

/**
 * The BoosterEffect is a shell around a Booster with a timer which times its duration.
 * When added to the list of active booster effects in Game, Game calls the booster's
 * startEffect() method. When the timer runs out Game calls endEffect() and
 * removes the BoosterEffect from its list.
 */

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

public class BoosterEffect implements IAlarmListener {
    public Booster booster;
    private Alarm effectTimer;

    public BoosterEffect(Booster booster){
        this.booster = booster;
        effectTimer = new Alarm("boosterEffectTimer", booster.getEffectDuration());
        effectTimer.addTarget(this);
        effectTimer.start();
    }

    @Override
    public void triggerAlarm(String s) {
        booster.game.removeBoosterEffect(this);
    }
}
