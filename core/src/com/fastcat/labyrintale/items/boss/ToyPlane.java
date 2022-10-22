package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class ToyPlane extends AbstractItem {

  private static final String ID = "ToyPlane";
  private static final ItemRarity RARITY = ItemRarity.BOSS;

  public ToyPlane(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    AbstractLabyrinth.charge++;
    owner.stat.spell -= 5;
    owner.stat.speed += 5;
  }

  @Override
  public void onRemove() {
    AbstractLabyrinth.charge--;
    owner.stat.spell += 5;
    owner.stat.speed -= 5;
  }
}
