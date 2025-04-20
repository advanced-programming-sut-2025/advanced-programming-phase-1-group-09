package models;

import models.GameWorld.*;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Enums.WeatherType;
import models.GameWorld.Map.Map.Map;

import java.util.ArrayList;

public class Game {
    private static int idCounter = 1;
    private final int id;
    private final Player curentPlayer;
    private final ArrayList<Player> players;
    private final User creator;
    private TimeState timeState;
    private Map map;
    private Season season;
    private Weather weather;

    public Game(int id, Player curentPlayer, ArrayList<Player> players, User creator) {
        this.id = id;
        this.curentPlayer = curentPlayer;
        this.players = players;
        this.creator = creator;
    }
}
