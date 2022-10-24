package com.fastcat.labyrintale.items.shop;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class BabyDish extends AbstractItem {

  private static final String ID = "BabyDish";
  private static final ItemRarity RARITY = ItemRarity.SHOP;

  public BabyDish(AbstractPlayer owner) {
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
