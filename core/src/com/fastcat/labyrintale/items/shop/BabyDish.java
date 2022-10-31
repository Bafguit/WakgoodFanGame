package com.fastcat.labyrintale.items.shop;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.items.starter.PlaceHolder;

public class BabyDish extends AbstractItem {

  private static final String ID = "BabyDish";
  private static final ItemRarity RARITY = ItemRarity.SHOP;

  public BabyDish(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.stat.attack += 1;
    owner.stat.spell += 1;
    for(int i = 0; i < 2; i++) {
      if(owner.item[i] == this) {
        owner.gainItem(new PlaceHolder(owner), i);
        break;
      }
    }
  }
}
