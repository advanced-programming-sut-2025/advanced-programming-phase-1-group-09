package models;

import models.GameWorld.*;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Map.Map.Map;

import java.util.ArrayList;

public class Game {
    private static int idCounter = 1;
    private final int id;
    private Player currentPlayer;
    private final ArrayList<Player> players;
    private final User creator;
    private TimeState timeState;
    private Map map;
    private final Season season;
    private final Weather weather;

    public Game(Player currentPlayer, ArrayList<Player> players, User creator) {
        this.id = idCounter++;
        this.currentPlayer = currentPlayer;
        this.players = players;
        this.creator = creator;
        this.timeState = new TimeState();
        this.season = new Season();
        this.weather = new Weather(this.season);
    }

    public int getId() {
        return id;
    }
}
