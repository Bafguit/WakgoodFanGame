package com.fastcat.labyrintale.items.shop;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Badge extends AbstractItem {

  private static final String ID = "Badge";
  private static final ItemRarity RARITY = ItemRarity.SHOP;

  public Badge(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.modifyMaxHealth(5);
    owner.stat.neutRes += 0.1f;
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-5);
    owner.stat.neutRes -= 0.1f;
  }
}
