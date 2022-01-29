package com.fastcat.labyrintale.rewards;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;

public class SkillRewardNormal extends SkillReward {

    public SkillRewardNormal(int cardAmount) {
        super(cardAmount, new Array<>(AbstractLabyrinth.players));
    }

    @Override
    public void takeReward() {

    }
}
