package models.GameWorld;

import models.TimeObserver;
import models.TimeSubject;

import java.util.ArrayList;

public class TimeState implements TimeSubject {
    private int hour;
    private int day;
    private final ArrayList<TimeObserver> observers;

    public TimeState() {
        hour = 9;
        day = 1;
        observers = new ArrayList<>();
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
        for (TimeObserver observer : observers) {
            observer.onTimeChange(this);
        }
    }

    public void updateTime(int hour) {
        this.hour += hour;
        if (this.hour > 22) {
            this.hour = 9;
            this.day++;
            notifyObservers();
        }
    }

    public int getDay() {
        return day;
    }
}
