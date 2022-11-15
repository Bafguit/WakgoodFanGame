package com.fastcat.labyrintale.items.shop;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.rooms.other.ShopRoom;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.shopRandom;

public class Badge extends AbstractItem {

  private static final String ID = "Badge";
  private static final ItemRarity RARITY = ItemRarity.SHOP;

  public Badge(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    if(AbstractLabyrinth.currentFloor.currentRoom.type == AbstractRoom.RoomType.SHOP) {
      ShopRoom s = (ShopRoom) AbstractLabyrinth.currentFloor.currentRoom;
      s.roll.price *= 0.5f;
    }
    owner.modifyMaxHealth(5);
    owner.stat.neutRes += 10;
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-5);
    owner.stat.neutRes -= 10;
  }
}
