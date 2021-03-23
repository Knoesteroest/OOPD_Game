package items;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

public class BoosterEffect implements IAlarmListener {
    public Booster booster;
    private Alarm alarm;

    public BoosterEffect(Booster booster){
        this.booster = booster;
        alarm = new Alarm("boosterEffectTimer", booster.getEffectDuration());
        alarm.addTarget(this);
        alarm.start();
    }

    @Override
    public void triggerAlarm(String s) {
        booster.game.removeBoosterEffect(this);
    }
}
