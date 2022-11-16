package com.fastcat.labyrintale.items.shop;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.rooms.other.ShopRoom;
import com.fastcat.labyrintale.screens.shop.ShopScreen;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.shopRandom;

public class Protection extends AbstractItem {

  private static final String ID = "Protection";
  private static final ItemRarity RARITY = ItemRarity.SHOP;

  public Protection(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    if(Labyrintale.getBaseScreen() instanceof ShopScreen) {
      ShopRoom s = Labyrintale.shopScreen.room;
      int i = shopRandom.random(0, 10);
      if (i < 6) {
        s.skills[i].price = 0;
      } else {
        s.items[i - 6].price = 0;
      }
    }
    owner.stat.moveRes += 5;
    owner.stat.neutRes += 5;
    owner.stat.debuRes += 5;
  }

  @Override
  public void onRemove() {
    owner.stat.moveRes -= 5;
    owner.stat.neutRes -= 5;
    owner.stat.debuRes -= 5;
  }
}
