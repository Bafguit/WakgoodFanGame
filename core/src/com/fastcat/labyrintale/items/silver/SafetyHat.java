package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class SafetyHat extends AbstractItem {

  private static final String ID = "SafetyHat";
  private static final ItemRarity RARITY = ItemRarity.SILVER;

  public SafetyHat(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.modifyMaxHealth(15);
    owner.stat.neutRes += 5;
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-15);
    owner.stat.neutRes -= 5;
  }
}
