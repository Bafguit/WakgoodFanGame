package com.fastcat.labyrintale.items.special;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Sanpellegrino extends AbstractItem {

  private static final String ID = "Sanpellegrino";
  private static final ItemRarity RARITY = ItemRarity.SPECIAL;

  public Sanpellegrino(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.modifyMaxHealth(10);
    owner.stat.speed += 3;
    owner.stat.critical += 30;
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-10);
    owner.stat.speed -= 3;
    owner.stat.critical -= 30;
  }
}
