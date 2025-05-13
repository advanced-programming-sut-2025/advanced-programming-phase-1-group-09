package models.GameWorld;

import models.GameWorld.Enums.Day;
import models.GameWorld.Enums.SeasonName;
import models.TimeObserver;
import models.TimeSubject;

import java.util.ArrayList;

public class TimeState implements TimeSubject {
    private int hour;
    private int day;
    private final ArrayList<TimeObserver> observers;
    private final Season season;
    private final Weather weather;

    public TimeState(Season season, Weather weather) {
        hour = 9;
        day = 0;
        observers = new ArrayList<>();
        this.season = season;
        this.weather = weather;
    }

    @Override
    public void addObserver(TimeObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(TimeObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        season.onTimeChange(this);
        weather.onTimeChange(this);
        for (TimeObserver observer : observers) {
            observer.onTimeChange(this);
        }
    }

    public void updateDate(int day) {
        this.day += day;
        for (int i = 0; i < day; i++) notifyObservers();
        this.hour = 9;
    }

    public void updateTime(int hour) {
        this.hour += hour;
        if (this.hour > 22) {
            this.hour = 9;
            this.day++;
            notifyObservers();
        }
    }

    public String getFormattedTime() {
        if (hour <= 12) {
            return String.format("%d:00 AM", hour);
        } else {
            return String.format("%d:00 PM", hour - 12);
        }
    }

    public String getFormattedDate(SeasonName season) {
        return String.format(
                "Total Days Played: %d\nYear: %d / Season: %s / Day: %d (%s)",
                day,
                day / (4 * Season.DAYS_PER_SEASON),
                season,
                day % Season.DAYS_PER_SEASON,
                getDayOfTheWeek()
        );
    }

    public String getFormattedDateTime(SeasonName season) {
        return getFormattedDate(season) + "\n" + getFormattedTime();
    }

    public int getDay() {
        return day;
    }

    public Day getDayOfTheWeek() {
        return Day.values()[day % 7];
    }

    public Season getSeason() {
        return season;
    }

    public Weather getWeather() {
        return weather;
    }
}
