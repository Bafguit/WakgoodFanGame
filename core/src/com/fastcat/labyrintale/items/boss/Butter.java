package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Butter extends AbstractItem {

  private static final String ID = "Butter";
  private static final ItemRarity RARITY = ItemRarity.BOSS;

  public Butter(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    AbstractLabyrinth.charge++;
    owner.stat.debuRes += 30;
  }

  @Override
  public void onRemove() {
    AbstractLabyrinth.charge--;
    owner.stat.debuRes -= 30;
  }
}
