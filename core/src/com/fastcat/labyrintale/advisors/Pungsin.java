package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.*;

public class Pungsin extends AbstractItem {

  private static final String ID = "pung";
  private static final ItemRarity RARITY = ItemRarity.ADVISOR;

  public Pungsin() {
    super(ID, null, RARITY);
  }

  @Override
  public void onGain() {
    for(AbstractPlayer p : AbstractLabyrinth.players) {
      p.stat.spell++;
    }
  }

  @Override
  public void onRemove() {
    for(AbstractPlayer p : AbstractLabyrinth.players) {
      p.stat.spell--;
    }
  }
}