package com.fastcat.labyrintale.rewards;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.handlers.GroupHandler;

import java.util.HashMap;

public abstract class SkillReward extends AbstractReward {

    public Array<Array<AbstractSkill>> group = new Array<>();

    public SkillReward() {
        super(RewardType.SKILL);
    }

    public enum SkillRewardType {
        COMMON, BRONZE, SILVER, GOLD, UPGRADE
    }
}
