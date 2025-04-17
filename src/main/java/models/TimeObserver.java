package models;

import models.GameWorld.TimeState;

public interface TimeObserver {
    void onTimeChange(TimeState newState);
}
