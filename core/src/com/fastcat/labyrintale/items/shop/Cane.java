package com.fastcat.labyrintale.items.shop;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Cane extends AbstractItem {

  private static final String ID = "Cane";
  private static final ItemRarity RARITY = ItemRarity.SHOP;

  public Cane(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.stat.multiply += 75;
  }

  @Override
  public void onRemove() {
    owner.stat.multiply -= 75;
  }
}
