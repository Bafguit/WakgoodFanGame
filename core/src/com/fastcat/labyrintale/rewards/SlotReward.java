package com.fastcat.labyrintale.rewards;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.screens.expselect.ExpSelectScreen;
import com.fastcat.labyrintale.screens.playerselect.GetSelectedPlayer;
import com.fastcat.labyrintale.screens.playerselect.PlayerSelectScreen;

public class SlotReward extends AbstractReward implements GetSelectedPlayer {



    public SlotReward() {
        super(RewardType.EXP);
        setInfo("스킬 슬롯 강화", "스킬 슬롯 강화");
    }

    @Override
    public void takeReward() {
        Labyrintale.addTempScreen(new PlayerSelectScreen(ExpSelectScreen.getPlayers(ExpReward.ExpType.SKILL_SLOT), this));
    }

    @Override
    public void playerSelected(AbstractPlayer player) {
        //TODO 스킬 슬롯 강화
    }
}
