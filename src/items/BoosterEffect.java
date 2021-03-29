package items;

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
