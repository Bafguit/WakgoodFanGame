package com.fastcat.labyrintale.rewards;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;
import com.fastcat.labyrintale.interfaces.GetSelectedSlot;
import com.fastcat.labyrintale.screens.healselect.HealSelectScreen;
import com.fastcat.labyrintale.screens.playerselect.PlayerSelectScreen;
import com.fastcat.labyrintale.screens.slotselect.SlotSelectScreen;

public class ExpReward extends AbstractReward implements GetSelectedSlot {

  private AbstractScreen sc;

  public ExpReward() {
    super(RewardType.EXP);
    setInfo("스킬 슬롯 강화", "스킬 슬롯을 하나 선택해 강화합니다.");
  }

  @Override
  public void takeReward() {
    Labyrintale.addTempScreen(new SlotSelectScreen(this, this));
  }

  @Override
  public void slotSelected(AbstractPlayer player, int index) {}
}
