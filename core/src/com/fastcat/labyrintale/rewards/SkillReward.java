package com.fastcat.labyrintale.rewards;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

public abstract class SkillReward extends AbstractReward {

    public Array<Array<AbstractSkill>> group = new Array<>();

    public SkillReward() {
        super(RewardType.SKILL);
    }

    public enum SkillRewardType {
        NORMAL, UPGRADE
    }
}
