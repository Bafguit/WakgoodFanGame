package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractItem;

public class Sophia extends AbstractItem {

  private static final String ID = "sophia";
  private static final ItemRarity RARITY = ItemRarity.ADVISOR;

  public Sophia() {
    super(ID, null, RARITY);
  }
}