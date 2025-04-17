package models.GameWorld;

import models.GameWorld.Enums.WeatherType;
import models.TimeObserver;

public class Weather implements TimeObserver {
    private WeatherType currentWeather;
    private WeatherType nextDayWeather;

    @Override
    public void onTimeChange(TimeState newState) {

    }
}
