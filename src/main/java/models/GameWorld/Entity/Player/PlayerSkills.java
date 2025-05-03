package models.GameWorld.Entity.Player;

import models.GameWorld.Skills.FarmingSkill;
import models.GameWorld.Skills.FishingSkill;
import models.GameWorld.Skills.ForagingSkill;
import models.GameWorld.Skills.MiningSkill;

public class PlayerSkills {
    private final FarmingSkill farmingSkill;
    private final FishingSkill fishingSkill;
    private final ForagingSkill foragingSkill;
    private final MiningSkill miningSkill;

    public PlayerSkills() {
        farmingSkill = new FarmingSkill();
        fishingSkill = new FishingSkill();
        foragingSkill = new ForagingSkill();
        miningSkill = new MiningSkill();
    }

    public FarmingSkill getFarmingSkill() {
        return farmingSkill;
    }

    public FishingSkill getFishingSkill() {
        return fishingSkill;
    }

    public ForagingSkill getForagingSkill() {
        return foragingSkill;
    }

    public MiningSkill getMiningSkill() {
        return miningSkill;
    }
}
