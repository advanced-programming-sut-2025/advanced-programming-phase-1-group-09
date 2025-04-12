package models;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String email;
    private String nickname;
    private final Gender gender;
    private Game activeGame;
    private int score;
    private ArrayList<Integer> gameIds;

    public User(String username, String password, String email, String nickname, Gender gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.gender = gender;
        this.activeGame = null;
        this.score = 0;
        this.gameIds = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Gender getGender() {
        return gender;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public ArrayList<Integer> getGameIds() {
        return gameIds;
    }

    public void addGameId(int gameId) {
        gameIds.add(gameId);
    }
}
