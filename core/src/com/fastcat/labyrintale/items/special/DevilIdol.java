package com.fastcat.labyrintale.items.special;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class DevilIdol extends AbstractItem {

  private static final String ID = "DevilIdol";
  private static final ItemRarity RARITY = ItemRarity.SPECIAL;

  public DevilIdol(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.minRes = 80;
  }

  @Override
  public void onRemove() {
    owner.minRes = 5;
  }
}
