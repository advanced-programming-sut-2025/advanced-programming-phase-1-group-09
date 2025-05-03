package models.Menu;

public enum GameMenuCommands implements Command {
    ExitGame("exit\\s+game"),
    NextTurn("next\\s+turn"),
    ShowTime("time"),
    ShowDate("date"),
    ShowDateTime("datetime"),
    ShowDay("day\\s+of\\s+the\\s+week"),
    ShowSeason("season"),
    ShowWeather("weather"),
    ShowWeatherForecast("weather\\s+forecast"),
    BuildGreenhouse("build\\s+greenhouse"),
    Walk("walk\\s+-l\\s+<\\S+, \\S+>"),
    PrintMap("print\\s+map\\s+-l\\s+<\\S+, \\S+>\\s+-s\\s+\\S+"),
    MapHelp("help\\s+reading\\s+map"),
    ShowEnergy("show\\s+energy"),
    ShowInventory("show\\s+inventory"),
    InventoryTrash("inventory\\s+trash\\s+(?=.*-i)(?=.*-n).*"),
    EquipTool("equip\\s+tool\\s+\\S+"),
    ShowCurrentTool("show\\s+current\\s+tool"),
    ShowAvailableTools("show\\s+available\\s+tools"),
    UpgradeTool("upgrade\\s+tool\\s+\\S+"),
    UseTool("use\\s+tool\\s+-d\\s+\\S+"),
    ShowCraftInfo("craftinfo\\s+-n\\s+\\S+"),
    Plant("plant\\s+(?=.*-s)(?=.*-d).*"),
    ShowPlant("show\\s+plant\\s+-l\\s+<\\S+, \\S+>"),
    Fertilize("fertilize\\s+(?=.*-f)(?=.*-d).*"),
    ShowCurrentWater("how\\s+much\\s+water"),
    ShowCraftingRecipes("show\\s+crafting\\s+recipes"),
    CraftItem("crafting\\s+craft\\s+\\S+"),
    PlaceItem("place\\s+item\\s+(?=.*-n)(?=.*-d).*"),
    TransferToRefrigerator("cooking\\s+refrigerator\\s+(put|pick)\\s+\\S+"),
    ShowCookingRecipes("show\\s+cooking\\s+recipes"),
    PrepareFood("cooking\\s+prepare\\s+\\S+"),
    EatFood("eat\\s+\\S+"),
    Build("build\\s+(?=.*-a)(?=.*-l).*"),
    BuyAnimal("buy\\s+animal\\s+(?=.*-a)(?=.*-n).*"),
    PetAnimal("pet\\s+-n\\s+\\S+"),
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
    Talk("talk\\s+(?=.*-u)(?=.*-m).*"),
    ShowTalkHistory("talk\\s+history\\s+-u\\s+\\S+"),
    SendGift("gift\\s+(?=.*-u)(?=.*-i)(?=.*-a).*"),
    ShowReceivedGifts("gift\\s+list"),
    RateGift("gift\\s+rate\\s+(?=.*-i)(?=.*-r).*"),
    ShowAllGiftsHistory("gift\\s+history\\s+-u\\s+\\S+"),
    Hug("hug\\s+-u\\s+\\S+"),
    Flower("flower\\s+-u\\s+\\S+"),
    AskMarriage("ask\\s+marriage\\s+(?=.*-u)(?=.*-r).*"),
    RespondToProposal("respond\\s+(accept|reject)\\s+-u\\s+\\S+"),
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
}
