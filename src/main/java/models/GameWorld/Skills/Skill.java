package models.GameWorld.Skills;

public abstract class Skill {
    protected int level = 0;
    protected int experience = 0;
    protected static final int MAX_LEVEL = 4;

    public void addExperience(int xp) {
        if (level >= MAX_LEVEL) return;

        experience += xp;
        while (experience >= requiredXpForNextLevel() && level < MAX_LEVEL) {
            experience -= requiredXpForNextLevel();
            level++;
        }

        if (level == MAX_LEVEL) {
            experience = 0;
        }
    }

    protected int requiredXpForNextLevel() {
        return 100 * level + 50;
    }

    public int getLevel() {
        return level;
    }

    public abstract String getName();
}
