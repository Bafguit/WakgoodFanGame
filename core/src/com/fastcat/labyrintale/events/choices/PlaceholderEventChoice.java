package com.fastcat.labyrintale.events.choices;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.rewards.HealReward;
import com.fastcat.labyrintale.screens.reward.RewardScreen;

public class PlaceholderEventChoice extends AbstractEvent.EventChoice {

    public PlaceholderEventChoice(String t) {
        super(t);
    }

    @Override
    protected void onSelect() {

    }

    @Override
    protected boolean condition() {
        return false;
    }
}
