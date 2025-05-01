package models.GameWorld;

import models.GameWorld.Enums.SeasonName;
import models.TimeObserver;

public class Season implements TimeObserver {
    private SeasonName currentSeason;

    public Season() {
        this.currentSeason = SeasonName.Spring;
    }

    @Override
    public void onTimeChange(TimeState newState) {
        int day = newState.getDay();
        int seasonIndex = (day - 1) / 28;
        SeasonName[] seasons = SeasonName.values();
        this.currentSeason = seasons[seasonIndex % seasons.length];
    }

    public SeasonName getCurrentSeason() {
        return currentSeason;
    }
}
