package com.fastcat.labyrintale.items.gold;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class ToyPlane extends AbstractItem {

  private static final String ID = "ToyPlane";
  private static final ItemRarity RARITY = ItemRarity.GOLD;

  public ToyPlane(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.stat.speed += 5;
    owner.stat.moveRes += 50;
  }

  @Override
  public void onRemove() {
    owner.stat.speed -= 5;
    owner.stat.moveRes -= 50;
  }
}
