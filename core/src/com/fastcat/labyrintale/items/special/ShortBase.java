package com.fastcat.labyrintale.items.special;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class ShortBase extends AbstractItem {

  private static final String ID = "ShortBase";
  private static final ItemRarity RARITY = ItemRarity.SPECIAL;

  public ShortBase(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.stat.speed += 10;
  }

  @Override
  public void onRemove() {
    owner.stat.speed -= 10;
  }
}
