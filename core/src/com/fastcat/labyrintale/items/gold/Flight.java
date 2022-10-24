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
    owner.modifyMaxHealth(20);
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-20);
  }
}
