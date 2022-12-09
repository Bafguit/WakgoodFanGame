package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Megaphone extends AbstractItem {

  private static final String ID = "Megaphone";
  private static final ItemRarity RARITY = ItemRarity.BOSS;

  public Megaphone(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    AbstractLabyrinth.charge++;
    owner.modifyMaxHealth(8);
  }

  @Override
  public void onRemove() {
    AbstractLabyrinth.charge--;
    owner.modifyMaxHealth(-8);
  }
}
