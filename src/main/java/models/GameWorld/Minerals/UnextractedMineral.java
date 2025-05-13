package models.GameWorld.Minerals;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Tools.Tool;
import models.GameWorld.Map.Elements.MapElement;
import models.GameWorld.Map.Tile;
import views.ConsoleColors;

public class UnextractedMineral implements MapElement {
    private final String name;
    private final int price;
    private final double spawningChance;
    private String symbol = ConsoleColors.WHITE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD_BRIGHT;
    private final int strength;
    private double damaged;

    public UnextractedMineral(String name, int price, double spawningChance, String symbol, int strength) {
        this.name = name;
        this.price = price;
        this.spawningChance = spawningChance;
        this.symbol += symbol;
        this.strength = strength;
        this.damaged = 0;
    }

    public int getPrice() {
        return price;
    }

    public double getSpawningChance() {
        return spawningChance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSymbol() {
        return ConsoleColors.BLUE_BACKGROUND_BRIGHT + ConsoleColors.WHITE_BOLD + symbol;
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

    @Override
    public void interact(Player player, Coordinate position) {
        Tool currentTool = player.getInventory().getCurrentTool();
        if (currentTool.getName().equals("Pickaxe")) {
           damaged += (1 + 0.3 * player.getSkills().getMiningSkill().getLevel()) *
                      (1 + 0.4 * currentTool.getLevel());

           if (damaged >= strength) {
               Tile tile = player.getFarm().getTile(position);
               tile.removeElement(this);
               tile.addElement(extract());

               if (player.getSkills().getMiningSkill().getLevel() >= 2) tile.addElement(extract());

               player.getSkills().getMiningSkill().addExperience(10);
           }
        }
    }

    @Override
    public UnextractedMineral clone() {
        return new UnextractedMineral(name, price, spawningChance, symbol, strength);
    }

    public Mineral extract() {
        return new Mineral(this);
    }
}
