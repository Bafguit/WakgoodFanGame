package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class AhpuHat extends AbstractItem {

  private static final String ID = "AhpuHat";
  private static final ItemRarity RARITY = ItemRarity.BOSS;

  public AhpuHat(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    AbstractLabyrinth.charge++;
    owner.stat.neutRes += 8;
    owner.stat.debuRes += 8;
    owner.stat.moveRes += 8;
  }

  @Override
  public void onRemove() {
    AbstractLabyrinth.charge--;
    owner.stat.neutRes -= 8;
    owner.stat.debuRes -= 8;
    owner.stat.moveRes -= 8;
  }
}
