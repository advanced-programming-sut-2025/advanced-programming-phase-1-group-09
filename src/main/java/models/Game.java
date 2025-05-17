package models;

import models.GameWorld.*;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Entity.Player.PlayerFriendship;
import models.GameWorld.Enums.SeasonName;
import models.GameWorld.Enums.WeatherType;
import models.GameWorld.Map.GameMap;

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

    public Game(ArrayList<Player> players, User creator, GameMap publicMap) {
        this.id = idCounter++;
        this.creator = creator;
        this.publicMap = publicMap;
        this.turn = 0;
        this.season = new Season();
        this.weather = new Weather(this.season);

        // Don't add season and weather to the timeState's observer list,
        // because they will be updated by the TimeState itself.
        this.timeState = new TimeState(season, weather);

        this.players = players;
        initializePlayersFriendship();
        for (Player player : players) {
            this.timeState.addObserver(player);
            this.timeState.addObserver(player.getField());
        }
        this.playersCount = players.size();

        this.timeState.addObserver(publicMap);
    }

    private void initializePlayersFriendship() {
        for (Player player1 : players) {
            for (Player player2 : players) {
                if (player1 == player2) continue;
                player1.getFriendships().put(player2.getUsername(), new PlayerFriendship(player2));
            }
        }
    }

    public int getId() {
        return id;
    }

    public Player getCurrentPlayer() {
        return players.get(turn);
    }

    public Player getPlayer(String username) {
        for (Player player : players) {
            if (player.getUsername().equals(username)) return player;
        }
        return null;
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
