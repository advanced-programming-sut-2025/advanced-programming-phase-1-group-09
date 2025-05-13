package models;

import models.GameWorld.*;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Enums.SeasonName;
import models.GameWorld.Enums.WeatherType;
import models.GameWorld.Map.GameMap;
import models.GameWorld.Map.PublicMap;

import java.util.ArrayList;

public class Game {
    private static int idCounter = 1;
    private final int id;
    private final ArrayList<Player> players;
    private final int playersCount;
    private final User creator;
    private final GameMap publicMap;
    private int turn;
    private final TimeState timeState;
    private final Season season;
    private final Weather weather;

    public Game(ArrayList<Player> players, User creator) {
        this.id = idCounter++;
        this.creator = creator;
        this.publicMap = new PublicMap();
        this.turn = 0;
        this.season = new Season();
        this.weather = new Weather(this.season);

        // Don't add season and weather to the timeState, because they will be updated by the TimeState.
        this.timeState = new TimeState(season, weather);

        this.players = players;
        for (Player player : players) {
            this.timeState.addObserver(player);
            this.timeState.addObserver(player.getFarm());
        }
        this.playersCount = players.size();

        this.timeState.addObserver(publicMap);
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
        return turn == (playersCount - 1);
    }

    public void nextTurn() {
        turn++;
        if (turn >= playersCount) {
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
