package com.fastcat.labyrintale.items.special;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class LoyalVirus extends AbstractItem {

  private static final String ID = "LoyalVirus";
  private static final ItemRarity RARITY = ItemRarity.SPECIAL;

  public LoyalVirus(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.modifyMaxHealth(5);
    owner.stat.speed -= 1;
    owner.stat.moveRes -= 10;
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-5);
    owner.stat.speed += 1;
    owner.stat.moveRes += 10;
  }
}
