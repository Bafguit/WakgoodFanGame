package com.fastcat.labyrintale.items.gold;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Flight extends AbstractItem {

  private static final String ID = "Flight";
  private static final ItemRarity RARITY = ItemRarity.GOLD;

  public Flight(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.stat.attack += 3;
    owner.stat.critical += 30;
    owner.stat.multiply += 30;
  }

  @Override
  public void onRemove() {
    owner.stat.attack -= 3;
    owner.stat.critical -= 30;
    owner.stat.multiply -= 30;
  }
}
