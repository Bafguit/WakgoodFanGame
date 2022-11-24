package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.LowHealAction;
import com.fastcat.labyrintale.skills.player.advisor.CarnarSkill;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class CarnarYungter extends AbstractItem {

  private static final String ID = "carnar";
  private static final ItemRarity RARITY = ItemRarity.ADVISOR;

  public CarnarYungter() {
    super(ID, null, RARITY);
  }

  @Override
  public void atBattleEnd() {
    top(new LowHealAction(2));
  }
}
