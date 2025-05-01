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
        this.currentWeather = WeatherType.Sunny;
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
            case Spring:
                if (chance < 0.5) return WeatherType.Sunny;
                else if (0.5 <= chance || chance < 0.8) return WeatherType.Rainy;
                else return WeatherType.Storm;
            case Summer:
                if (chance < 0.85) return WeatherType.Sunny;
                else if (0.85 <= chance || chance < 0.9) return WeatherType.Rainy;
                else return WeatherType.Storm;
            case Fall:
                if (chance < 0.3) return WeatherType.Sunny;
                else if (0.3 <= chance || chance < 0.7) return WeatherType.Rainy;
                else if (0.7 <= chance || chance < 0.8) return WeatherType.Storm;
                else return WeatherType.Snowy;
            case Winter:
                if (chance < 0.3) return WeatherType.Sunny;
                else if (0.3 <= chance || chance < 0.6) return WeatherType.Rainy;
                else if (0.6 <= chance || chance < 0.75) return WeatherType.Storm;
                else return WeatherType.Snowy;
            default:
                return WeatherType.Sunny;
        }
    }

    public WeatherType getCurrentWeather() {
        return currentWeather;
    }

    public WeatherType getNextDayWeather() {
        return nextDayWeather;
    }
}
