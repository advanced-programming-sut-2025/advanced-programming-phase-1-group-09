package models.Menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands implements Command {
    ExitGame("exit\\s+game"),
    WhichMap("which\\s+map"),
    NextTurn("next\\s+turn"),
    ShowTime("time"),
    ShowDate("date"),
    ShowDateTime("datetime"),
    ShowDay("day\\s+of\\s+the\\s+week"),
    ShowSeason("season"),
    ShowWeather("weather"),
    ShowWeatherForecast("weather\\s+forecast"),
    BuildGreenhouse("build\\s+greenhouse"),
    Walk("walk\\s+-y\\s+\\d+\\s+-x\\s+\\d+"),
    GoPublic("go\\s+public"),
    GoHome("go\\s+home"),
    PrintMap("print\\s+map\\s+-s\\s+\\d+"),
    MapHelp("help\\s+reading\\s+map"),
    ShowEnergy("show\\s+energy"),
    ShowBalance("show\\s+balance"),
    ShowInventory("show\\s+inventory"),
    InventoryTrash("inventory\\s+trash\\s+(?=.*-i).*"),
    EquipTool("equip\\s+tool\\s+\\S+"),
    ShowCurrentTool("show\\s+current\\s+tool"),
    ShowAvailableTools("show\\s+available\\s+tools"),
    UpgradeTool("upgrade\\s+tool\\s+\\S+"),
    UseTool("use\\s+tool\\s+-d\\s+\\S+"),
    ShowCraftInfo("craftinfo\\s+-n\\s+.+"),
    ShowAllCrops("show\\s+all\\s+crops"),
    ShowAllTrees("show\\s+all\\s+trees"),
    Plant("plant\\s+-s\\s+\\S+\\s+-d\\s+\\S+"),
    ShowPlant("show\\s+plant\\s+-y\\s+\\d+\\s+-x\\s+\\d+"),
    Fertilize("fertilize\\s+(?=.*-f)(?=.*-d).*"),
    ShowCurrentWater("how\\s+much\\s+water"),
    ShowCraftingRecipes("show\\s+crafting\\s+recipes"),
    CraftItem("crafting\\s+craft\\s+\\S+"),
    PlaceItem("place\\s+item\\s+(?=.*-n)(?=.*-d).*"),
    TransferToRefrigerator("cooking\\s+refrigerator\\s+(put|pick)\\s+\\S+"),
    ShowCookingRecipes("show\\s+cooking\\s+recipes"),
    PrepareFood("cooking\\s+prepare\\s+\\S+"),
    EatFood("eat\\s+\\S+"),
    Build("build\\s+-s\\s+(?<buildingName>.*)\\s+-l\\s+(?<x>\\d+)\\s+,\\s+(?<y>\\d+)"),
    BuyAnimal("buy\\s+animal\\s+-a\\s+(?<animal>.*)\\s+-n\\s+(?<name>.*)"),
    PetAnimal("pet\\s+-n\\s+(?<name>.*)"),
    ShowMyAnimals("animals"),
    ShepherdAnimals("shepherd\\s+animals\\s+(?=.*-n)(?=.*-l).*"),
    FeedAnimals("feed\\s+hay\\s+hay\\s+-n\\s+\\S+"),
    ShowUnpickedProduct("products"),
    CollectProduct("collect\\s+product\\s+-n\\s+\\S+"),
    SellAnimal("sell\\s+animal\\s+-n\\s+\\S+"),
    Fishing("fishing\\s+-p\\s+\\S+"),
    ArtisanUse("artisan\\s+use\\s+\\S+\\s+\\S+"),
    ArtisanGet("artisan\\s+get\\s+\\S+"),
    ShowProducts("show\\s+all\\s+products"),
    ShowAvailableProducts("show\\s+all\\s+available\\s+products"),
    Purchase("purchase\\s+\\S+\\s+-n\\s+\\S+"),
    Sell("sell\\s+\\S+\\s+-n\\s+\\S+"),
    ShowFriendships("friendships"),
    Talk("talk\\s+-u\\s+(?<username>.*)\\s+-m\\s+(?<message>.*)"),
    ShowNewMessages("new\\s+messages\\s+-u\\s+\\S+"),
    ShowTalkHistory("talk\\s+history\\s+-u\\s+\\S+"),
    SendGift("gift\\s+-u\\s+\\S+\\s+-i\\s+\\S+\\s+-a\\s+\\d+"),
    ShowReceivedGifts("gift\\s+list"),
    RateGift("gift\\s+rate\\s+-i\\s+\\d+\\s+-r\\s+\\d+"),
    ShowGifts("gift\\s+history\\s+-u\\s+\\S+"),
    Hug("hug\\s+-u\\s+\\S+"),
    Flower("flower\\s+-u\\s+\\S+"),
    AskMarriage("ask\\s+marriage\\s+-u\\s+\\S+"),
    RespondToProposal("respond\\s+-r\\s+(accept|reject)\\s+-u\\s+\\S+"),
    GoToTradeMenu("start\\s+trade"),
    MeetNPC("meet\\s+NPC\\s+\\S+"),
    GiftNPC("gift\\s+NPC\\s+\\S+\\s+-i\\s+\\S+"),
    ShowNPCFriendship("list\\s+NPC\\s+friendship"),
    ShowQuests("list\\s+quests"),
    FinishQuest("finish\\s+quest\\s+-i\\s+\\S+");

    private final String regex;

    GameMenuCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }

    public Matcher matcher(String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if(matcher.matches())
            return matcher;
        return null;
    }
}
