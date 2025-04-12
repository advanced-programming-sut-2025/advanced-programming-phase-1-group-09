package models;

import models.game_attributes.*;

import java.util.ArrayList;

public class Game {
    private static int idCounter = 1;
    private final int id;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private DateTime dateTime;
    private Weather weather;

    public Game(ArrayList<User> users) {
        this.id = idCounter++;
        this.players = new ArrayList<>();
        for (User user : users) {
            this.players.add(new Player(user));
        }
        this.dateTime = new DateTime();
        this.weather = Weather.SUNNY;
    }
}
