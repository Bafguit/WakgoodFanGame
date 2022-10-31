package com.fastcat.labyrintale.items.special;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class GolemHead extends AbstractItem {

  private static final String ID = "GolemHead";
  private static final ItemRarity RARITY = ItemRarity.SPECIAL;

  public GolemHead(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.stat.speed -= 100;
    owner.stat.neutRes += 100;
    owner.stat.debuRes += 100;
    owner.stat.moveRes += 100;
  }

  @Override
  public void onRemove() {
    owner.stat.speed += 100;
    owner.stat.neutRes -= 100;
    owner.stat.debuRes -= 100;
    owner.stat.moveRes -= 100;
  }
}
