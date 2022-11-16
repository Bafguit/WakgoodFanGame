package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Purplight extends AbstractItem {

  private static final String ID = "Purplight";
  private static final ItemRarity RARITY = ItemRarity.SILVER;

  public Purplight(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.stat.attack += 3;
    owner.stat.speed += 3;
    owner.stat.debuRes -= 20;
  }

  @Override
  public void onRemove() {
    owner.stat.attack -= 3;
    owner.stat.speed -= 3;
    owner.stat.debuRes += 20;
  }
}
