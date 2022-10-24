package com.fastcat.labyrintale.items.bronze;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Tail extends AbstractItem {

  private static final String ID = "Tail";
  private static final ItemRarity RARITY = ItemRarity.BRONZE;

  public Tail(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.modifyMaxHealth(3);
    owner.stat.debuRes += 3;
    owner.stat.neutRes += 3;
    owner.stat.moveRes += 3;
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-3);
    owner.stat.debuRes -= 3;
    owner.stat.neutRes -= 3;
    owner.stat.moveRes -= 3;
  }
}
