package com.fastcat.labyrintale.rewards;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;
import com.fastcat.labyrintale.interfaces.GetSelectedSlot;
import com.fastcat.labyrintale.screens.healselect.HealSelectScreen;
import com.fastcat.labyrintale.screens.playerselect.PlayerSelectScreen;
import com.fastcat.labyrintale.screens.slotselect.SlotSelectScreen;

public class SlotReward extends AbstractReward implements GetSelectedPlayer, GetSelectedSlot {

  public GetSelectedSlot gets;

  public SlotReward() {
    super(RewardType.EXP);
    setInfo("스킬 슬롯 강화", "스킬 슬롯 강화");
  }

  @Override
  public void takeReward() {
    Labyrintale.addTempScreen(
        new PlayerSelectScreen(HealSelectScreen.getPlayers(HealReward.HealType.SKILL_SLOT), this));
  }

  @Override
  public void playerSelected(AbstractPlayer player) {
    Labyrintale.addTempScreen(new SlotSelectScreen(player, this));
  }

  @Override
  public void slotSelected(AbstractPlayer player, int index) {
    if (gets != null) gets.slotSelected(player, index);
  }
}
