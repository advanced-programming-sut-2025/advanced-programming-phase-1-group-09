package models.GameWorld;

import models.TimeObserver;
import models.TimeSubject;

import java.util.ArrayList;

public class TimeState implements TimeSubject {
    private int hour;
    private int day;
    private ArrayList<TimeObserver> observers;

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
}
