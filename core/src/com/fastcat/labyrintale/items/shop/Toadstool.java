package com.fastcat.labyrintale.items.shop;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.items.starter.PlaceHolder;

public class Toadstool extends AbstractItem {

  private static final String ID = "Toadstool";
  private static final ItemRarity RARITY = ItemRarity.SHOP;

  public Toadstool(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.stat.neutRes += 5;
    owner.stat.debuRes += 5;
    owner.stat.moveRes += 5;
    for(int i = 0; i < 2; i++) {
      if(owner.item[i] == this) {
        owner.gainItem(new PlaceHolder(owner), i);
        break;
      }
    }
  }
}
