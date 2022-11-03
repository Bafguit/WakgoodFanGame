package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class BunnyHat extends AbstractItem {

  private static final String ID = "BunnyHat";
  private static final ItemRarity RARITY = ItemRarity.SILVER;

  public BunnyHat(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.stat.attack += 1;
    owner.stat.spell += 1;
    owner.stat.critical += 20;
  }

  @Override
  public void onRemove() {
    owner.stat.attack -= 1;
    owner.stat.spell -= 1;
    owner.stat.critical -= 20;
  }
}
