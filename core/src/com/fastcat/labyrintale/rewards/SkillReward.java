package com.fastcat.labyrintale.rewards;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.handlers.GroupHandler;

import java.util.HashMap;

import static com.fastcat.labyrintale.rewards.SkillReward.SkillRewardType.COMMON;

public abstract class SkillReward extends AbstractReward {

    public HashMap<AbstractPlayer, Array<AbstractSkill>> group = new HashMap<>();

    public SkillReward(int cardAmount, Array<AbstractPlayer> ap) {
        super(RewardType.SKILL);
        for(AbstractPlayer p : ap) {
            group.put(p, GroupHandler.SkillGroup.getRandomSkill(p, COMMON, cardAmount));
        }
    }

    public enum SkillRewardType {
        COMMON, BRONZE, SILVER, GOLD,
    }
}
