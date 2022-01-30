package com.fastcat.labyrintale.rewards;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.screens.reward.skill.SkillRewardScreen;

public class SkillRewardNormal extends SkillReward {

    public SkillRewardNormal(int cardAmount) {
        super(cardAmount, new Array<>(AbstractLabyrinth.players));
    }

    @Override
    public void takeReward() {
        Labyrintale.addTempScreen(new SkillRewardScreen(SkillRewardType.COMMON, this));
    }
}
