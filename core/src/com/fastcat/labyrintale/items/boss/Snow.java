package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Snow extends AbstractItem {

  private static final String ID = "Snow";
  private static final ItemRarity RARITY = ItemRarity.BOSS;

  public Snow(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    AbstractLabyrinth.charge++;
    owner.stat.spell += 1;
  }

  @Override
  public void onRemove() {
    AbstractLabyrinth.charge--;
    owner.stat.spell -= 1;
  }
}
