package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import models.GameWorld.Enums.Gender;

import java.util.ArrayList;

public class User {
    private String username;
    private String hashedPassword;
    private String nickname;
    private String email;
    private final Gender gender;
    private String securityQuestion;
    private String securityAnswer;
    private transient Game activeGame;
    private int score;
    private ArrayList<Integer> gameIds;
    private int numberOfGamesPlayed;
    private int maximumMoneyGained;

    @JsonCreator
    public User(
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("nickname") String nickname,
            @JsonProperty("email") String email,
            @JsonProperty("gender") Gender gender
    ) {
        this.username = username;
        this.hashedPassword = password;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.activeGame = null;
        this.score = 0;
        this.gameIds = new ArrayList<>();
        this.numberOfGamesPlayed = 0;
        this.maximumMoneyGained = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
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

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
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

    public int getNumberOfGamesPlayed() {
        return numberOfGamesPlayed;
    }

    public int getMaximumMoneyGained() {
        return maximumMoneyGained;
    }
}
