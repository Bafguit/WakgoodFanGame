package com.fastcat.labyrintale.events.choices;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.rewards.HealReward;
import com.fastcat.labyrintale.rewards.SkillRewardNormal;
import com.fastcat.labyrintale.screens.reward.RewardScreen;

public class SkillRewardEventChoice extends AbstractEvent.EventChoice {

    public SkillRewardEventChoice(AbstractEvent e, String t) {
        super(e, t);
    }

    @Override
    protected void onSelect() {
        Array<AbstractReward> temp = new Array<>();
        temp.add(new SkillRewardNormal(2));
        Labyrintale.addTempScreen(new RewardScreen(RewardScreen.RewardScreenType.EVENT, temp));
    }
}
