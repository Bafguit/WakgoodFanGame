package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class LifePotion extends AbstractItem {

  private static final String ID = "LifePotion";
  private static final ItemRarity RARITY = ItemRarity.SILVER;

  public LifePotion(AbstractPlayer owner) {
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
