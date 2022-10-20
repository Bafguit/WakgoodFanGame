package com.fastcat.labyrintale.rewards;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.screens.healselect.HealSelectScreen;

public class HealReward extends AbstractReward {

  public HealReward() {
    super(RewardType.HEAL);
    setInfo("회복", "아무튼 회복");
  }

  @Override
  public void takeReward() {
    Labyrintale.addTempScreen(new HealSelectScreen());
  }

  public enum HealType {
    SKILL_SLOT,
    HEAL,
    MAX_HEALTH,
    REVIVE
  }
}
