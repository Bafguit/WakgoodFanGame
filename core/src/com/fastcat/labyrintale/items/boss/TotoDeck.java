package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class TotoDeck extends AbstractItem {

  private static final String ID = "TotoDeck";
  private static final ItemRarity RARITY = ItemRarity.BOSS;

  public TotoDeck(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.stat.multiply += 250;
  }

  @Override
  public void onRemove() {
    owner.stat.multiply -= 250;
  }
}
