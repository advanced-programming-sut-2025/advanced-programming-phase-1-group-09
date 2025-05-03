package models.GameWorld;

import models.GameWorld.Enums.SeasonName;
import models.TimeObserver;

public class Season implements TimeObserver {
    public static final int DAYS_PER_SEASON = 28;
    private SeasonName currentSeason;

    public Season() {
        this.currentSeason = SeasonName.SPRING;
    }

    @Override
    public void onTimeChange(TimeState newState) {
        int day = newState.getDay();
        int seasonIndex = day / DAYS_PER_SEASON;
        SeasonName[] seasons = SeasonName.values();
        this.currentSeason = seasons[seasonIndex % seasons.length];
    }

    public SeasonName getCurrentSeason() {
        return currentSeason;
    }
}
