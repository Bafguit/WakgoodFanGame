package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Hwacha extends AbstractItem {

  private static final String ID = "Hwacha";
  private static final ItemRarity RARITY = ItemRarity.BOSS;

  public Hwacha(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    AbstractLabyrinth.charge++;
    owner.stat.attack += 2;
  }

  @Override
  public void onRemove() {
    AbstractLabyrinth.charge--;
    owner.stat.attack -= 2;
  }
}
