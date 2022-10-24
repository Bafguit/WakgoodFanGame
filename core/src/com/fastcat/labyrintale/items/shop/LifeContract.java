package com.fastcat.labyrintale.items.shop;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class LifeContract extends AbstractItem {

  private static final String ID = "LifeContract";
  private static final ItemRarity RARITY = ItemRarity.SHOP;

  public LifeContract(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.stat.speed -= 1;
    owner.stat.critical += 10;
    owner.stat.moveRes += 10;
    owner.stat.debuRes += 10;
    owner.stat.neutRes += 10;
  }

  @Override
  public void onRemove() {
    owner.stat.speed += 1;
    owner.stat.critical -= 10;
    owner.stat.moveRes -= 10;
    owner.stat.debuRes -= 10;
    owner.stat.neutRes -= 10;
  }
}
