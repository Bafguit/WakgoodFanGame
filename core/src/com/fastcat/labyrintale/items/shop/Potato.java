package com.fastcat.labyrintale.items.shop;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Potato extends AbstractItem {

  private static final String ID = "Potato";
  private static final ItemRarity RARITY = ItemRarity.SHOP;

  public Potato(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.modifyMaxHealth(5);
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-5);
  }
}
