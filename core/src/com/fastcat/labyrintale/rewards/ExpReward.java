package com.fastcat.labyrintale.rewards;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.events.choices.AtEndOfTempScreen;
import com.fastcat.labyrintale.screens.expselect.ExpSelectScreen;
import com.fastcat.labyrintale.screens.playerselect.GetSelectedPlayer;

public class ExpReward extends AbstractReward {

    public ExpReward() {
        super(RewardType.EXP);
        setInfo("경험치", "아무튼 경험치");
    }

    @Override
    public void takeReward() {
        Labyrintale.addTempScreen(new ExpSelectScreen());
    }

    public enum ExpType {
        SKILL_SLOT, HEAL, MAX_HEALTH, REVIVE
    }
}
