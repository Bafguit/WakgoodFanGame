package com.fastcat.labyrintale.items.gold;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class IceFrag extends AbstractItem {

  private static final String ID = "IceFrag";
  private static final ItemRarity RARITY = ItemRarity.GOLD;

  public IceFrag(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    AbstractLabyrinth.charge++;
    owner.modifyMaxHealth(5);
  }

  @Override
  public void onRemove() {
    AbstractLabyrinth.charge--;
    owner.modifyMaxHealth(-5);
  }
}
