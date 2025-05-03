package models;

import models.GameWorld.*;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Enums.SeasonName;
import models.GameWorld.Enums.WeatherType;
import models.GameWorld.Map.Map.Map;

import java.util.ArrayList;

public class Game {
    private static int idCounter = 1;
    private final int id;
    private final ArrayList<Player> players;
    private final User creator;
    private int turn;
    private final TimeState timeState;
    private Map map;
    private final Season season;
    private final Weather weather;

    public Game(ArrayList<Player> players, User creator) {
        this.id = idCounter++;
        this.creator = creator;
        this.turn = 0;
        this.timeState = new TimeState();

        this.players = players;
        for (Player player : players) {
            this.timeState.addObserver(player);
        }

        this.season = new Season();
        this.timeState.addObserver(season);

        this.weather = new Weather(this.season);
        this.timeState.addObserver(weather);
    }

    public int getId() {
        return id;
    }

    public Player getCurrentPlayer() {
        return players.get(turn);
    }

    public boolean areAllPlayersFainted() {
        for (Player player : players) {
            if (!player.isFainted()) return false;
        }
        return true;
    }

    public boolean isItLastTurn() {
        return turn == (players.size() - 1);
    }

    public void nextTurn() {
        turn++;
        if (turn >= players.size()) {
            resetTurn();
        }
    }

    public void resetTurn() {
        turn = 0;
    }

    public TimeState getTimeState() {
        return timeState;
    }

    public SeasonName getCurrentSeason() {
        return season.getCurrentSeason();
    }

    public Weather getWeather() {
        return weather;
    }

    public void changeCurrentWeather(WeatherType newWeather) {
        weather.setCurrentWeather(newWeather);
    }
}
