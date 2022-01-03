package com.fastcat.labyrintale.rewards;

import com.fastcat.labyrintale.abstracts.AbstractReward;

public class GoldReward extends AbstractReward {

    public int gold;

    public GoldReward(int gold) {
        super(RewardType.GOLD);
        this.gold = gold;
    }

    @Override
    public void takeReward() {

    }
}
