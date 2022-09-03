package com.fastcat.labyrintale.rewards;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;
import com.fastcat.labyrintale.interfaces.GetSelectedSlot;
import com.fastcat.labyrintale.screens.healselect.HealSelectScreen;
import com.fastcat.labyrintale.screens.playerselect.PlayerSelectScreen;
import com.fastcat.labyrintale.screens.slotselect.SlotSelectScreen;
import com.fastcat.labyrintale.screens.statselect.StatSelectScreen;

public class StatReward extends AbstractReward {

    public GetSelectedSlot gets;

    public StatReward() {
        super(RewardType.STAT);
        setInfo("능력치 강화", "능력치를 선택해 강화합니다.");
    }

    @Override
    public void takeReward() {
        Labyrintale.addTempScreen(new StatSelectScreen());
    }
}
