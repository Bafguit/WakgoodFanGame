package com.fastcat.labyrintale.rewards;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractPlayer.PlayerClass;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.handlers.GroupHandler;

public class SkillReward extends AbstractReward {

    public Array<AbstractSkill> skills;

    public SkillReward(Array<AbstractSkill> skills) {
        super(RewardType.SKILL);
        this.skills = skills;
    }

    public SkillReward(PlayerClass cls, SkillRewardType type, int amount) {
        super(RewardType.SKILL);
        skills = GroupHandler.SkillGroup.getRandomSkill(cls, type, amount);
    }

    @Override
    public void takeReward() {

    }

    public enum SkillRewardType {
        COMMON, BRONZE, SILVER, GOLD,
    }
}
