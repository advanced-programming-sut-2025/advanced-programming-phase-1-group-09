package models.GameWorld.Items.Tools.AchievableTools;

import models.GameWorld.Items.Tools.Tool;

public abstract class AchievableTool extends Tool {
    protected int price;

    public AchievableTool(String name, int price) {
        super(name);
        this.price = price;
    }
}
