package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.actions.LowHealAction;

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
