package models.GameWorld.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Shops {
    blacksmith("blacksmith"),
    MarnieRanch("Marnie's Ranch"),
    TheStarDropSaloon("The Stardrop Saloon"),
    CarpenterShop("Carpenter's Shop"),
    JojaMart("JojaMart"),
    PierreGeneralStore("Pierre's General Store"),
    FishShop("Fish Shop");

    private final String name;
    Shops(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
