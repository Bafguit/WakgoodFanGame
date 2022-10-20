package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Hwacha extends AbstractItem {

  private static final String ID = "Hwacha";
  private static final ItemRarity RARITY = ItemRarity.BOSS;

  public Hwacha(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.modifyMaxHealth(10);
    owner.stat.attack += 5;
    owner.stat.spell -= 5;
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-10);
    owner.stat.attack -= 5;
    owner.stat.spell += 5;
  }
}
