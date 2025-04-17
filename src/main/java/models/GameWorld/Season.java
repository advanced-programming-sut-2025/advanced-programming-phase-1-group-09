package models.GameWorld;

import models.GameWorld.Enums.SeasonName;
import models.TimeObserver;

public class Season implements TimeObserver {
    private SeasonName currentSeason;

    @Override
    public void onTimeChange(TimeState newState) {

    }
}
