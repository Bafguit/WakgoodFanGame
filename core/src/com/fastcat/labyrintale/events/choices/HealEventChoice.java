package com.fastcat.labyrintale.events.choices;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.rewards.HealReward;
import com.fastcat.labyrintale.screens.rest.RestButton;
import com.fastcat.labyrintale.screens.reward.RewardScreen;

public class HealEventChoice extends AbstractEvent.EventChoice {

    private final AbstractEntity[] target;
    private final int amount;

    public HealEventChoice(String t, AbstractEntity[] target, int amount) {
        super(t);
        this.target = target;
        this.amount = amount;
    }

    @Override
    protected void onSelect() {
        Array<AbstractReward> temp = new Array<>();
        temp.add(new HealReward(amount));
        Labyrintale.addTempScreen(new RewardScreen(RewardScreen.RewardScreenType.EVENT, temp));
    }
}
