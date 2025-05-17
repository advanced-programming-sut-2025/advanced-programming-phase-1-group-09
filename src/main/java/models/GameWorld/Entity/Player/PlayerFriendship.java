package models.GameWorld.Entity.Player;

import models.GameWorld.Entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerFriendship {
    public static final int MAX_LEVEL = 4;

    private final Entity entity;
    private int experience;
    private int level;
    private boolean isGreeted;
    private final HashMap<Integer, Boolean> specialRequirements;
    private final ArrayList<String> messageHistory;
    private int lastMessageSeen;
    private final ArrayList<Gift> sentGifts;
    private final ArrayList<Gift> receivedGifts;
    private final ArrayList<String> proposalHistory;
    private int lastProposalSeen;

    public PlayerFriendship(Entity entity) {
        this.entity = entity;
        this.experience = 0;
        this.level = 0;
        this.isGreeted = false;

        this.specialRequirements = new HashMap<>();
        this.specialRequirements.put(2, false);
        this.specialRequirements.put(3, false);

        this.messageHistory = new ArrayList<>();
        this.lastMessageSeen = 0;

        this.sentGifts = new ArrayList<>();
        this.receivedGifts = new ArrayList<>();

        this.proposalHistory = new ArrayList<>();
        this.lastProposalSeen = 0;
    }

    public void addExperience(int xp) {
        if (level >= MAX_LEVEL || isGreeted) return;

        experience += xp;
        while (experience >= requiredXpForNextLevel() && level < MAX_LEVEL) {
            if (!isLevelUpAllowed(level)) {
                experience = requiredXpForNextLevel();
                // Don't level up until the special requirement is met
                break;
            }

            experience -= requiredXpForNextLevel();
            level++;
        }

        if (level == MAX_LEVEL) {
            experience = 0;
        }
    }

    public boolean isLevelUpAllowed(int nextLevel) {
        if (!specialRequirements.containsKey(nextLevel)) return true;
        return specialRequirements.get(nextLevel);
    }

    public void fulfillSpecialRequirement(int level) {
        if (specialRequirements.containsKey(level)) {
            specialRequirements.put(level, true);
        }
    }

    public void reduceExperience(int xp) {
        if (level == MAX_LEVEL) return;

        while (xp > experience && level > 0) {
            xp -= experience;
            level--;
            experience = requiredXpForNextLevel();
        }

        if (xp > 0) experience -= Math.min(xp, experience);
    }

    public int requiredXpForNextLevel() {
        return (level + 1) * 100;
    }

    public boolean isLevelMaxed() {
        return requiredXpForNextLevel() <= experience;
    }

    public Entity getEntity() {
        return entity;
    }

    public int getLevel() {
        return level;
    }

    public void resetLevel() {
        level = 0;
        experience = 0;
    }

    public boolean isMaxLevel() {
        return level == MAX_LEVEL;
    }

    public boolean isGreeted() {
        return isGreeted;
    }

    public void setGreeted(boolean greeted) {
        this.isGreeted = greeted;
    }

    public void addMessage(String message) {
        messageHistory.add(message);
        if (!isGreeted) {
            addExperience(20);
            isGreeted = true;
        }
    }

    public ArrayList<String> getMessageHistory() {
        return messageHistory;
    }

    public ArrayList<String> getNewMessages() {
        ArrayList<String> messages = new ArrayList<>();
        for (int i = lastMessageSeen; i < messageHistory.size(); i++) {
            messages.add(messageHistory.get(i));
        }
        lastMessageSeen = messageHistory.size();
        return messages;
    }

    public int countNewMessages() {
        return messageHistory.size() - lastMessageSeen;
    }

    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public Gift findReceivedGift(int giftId) {
        for (Gift gift : receivedGifts) {
            if (gift.getId() == giftId) return gift;
        }
        return null;
    }

    public ArrayList<Gift> getSentGifts() {
        return sentGifts;
    }

    public ArrayList<String> getProposalHistory() {
        return proposalHistory;
    }

    public ArrayList<String> getLastProposals() {
        ArrayList<String> proposals = new ArrayList<>();
        for (int i = lastProposalSeen; i < proposalHistory.size(); i++) {
            proposals.add(proposalHistory.get(i));
        }
        lastProposalSeen = proposalHistory.size();
        return proposals;
    }

    public int countNewProposals() {
        return proposalHistory.size() - lastProposalSeen;
    }

    @Override
    public String toString() {
        return String.format("Entity: %s\nLevel: %d\nExperience: %d\nIs Greeted: %s",
                entity.getName(), level, experience, isGreeted);
    }
}
