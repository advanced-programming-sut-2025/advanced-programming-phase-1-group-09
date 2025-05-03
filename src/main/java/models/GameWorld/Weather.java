package models.GameWorld;

import models.GameWorld.Enums.WeatherType;
import models.TimeObserver;

import java.util.Random;

public class Weather implements TimeObserver {
    private WeatherType currentWeather;
    private WeatherType nextDayWeather;
    private final Season season;

    public Weather(Season season) {
        this.season = season;
        this.currentWeather = WeatherType.SUNNY;
        this.nextDayWeather = generateWeather();
    }

    @Override
    public void onTimeChange(TimeState newState) {
        this.currentWeather = this.nextDayWeather;
        this.nextDayWeather = generateWeather();
    }

    private WeatherType generateWeather() {
        Random rand = new Random();
        double chance = rand.nextDouble();

        switch (season.getCurrentSeason()) {
            case SPRING:
                if (chance < 0.5) return WeatherType.SUNNY;
                else if (0.5 <= chance || chance < 0.8) return WeatherType.RAINY;
                else return WeatherType.STORM;
            case SUMMER:
                if (chance < 0.85) return WeatherType.SUNNY;
                else if (0.85 <= chance || chance < 0.9) return WeatherType.RAINY;
                else return WeatherType.STORM;
            case FALL:
                if (chance < 0.3) return WeatherType.SUNNY;
                else if (0.3 <= chance || chance < 0.7) return WeatherType.RAINY;
                else if (0.7 <= chance || chance < 0.8) return WeatherType.STORM;
                else return WeatherType.SNOWY;
            case WINTER:
                if (chance < 0.3) return WeatherType.SUNNY;
                else if (0.3 <= chance || chance < 0.6) return WeatherType.RAINY;
                else if (0.6 <= chance || chance < 0.75) return WeatherType.STORM;
                else return WeatherType.SNOWY;
            default:
                return WeatherType.SUNNY;
        }
    }

    public WeatherType getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(WeatherType currentWeather) {
        this.currentWeather = currentWeather;
    }

    public WeatherType getNextDayWeather() {
        return nextDayWeather;
    }
}
