package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Gyeruek extends AbstractItem {

  private static final String ID = "Gyeruek";
  private static final ItemRarity RARITY = ItemRarity.BOSS;

  public Gyeruek(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.modifyMaxHealth(10);
    owner.stat.attack += 1;
    owner.stat.spell += 1;
    owner.stat.speed += 1;
    owner.stat.critical += 10;
    owner.stat.multiply += 10;
    owner.stat.neutRes += 10;
    owner.stat.debuRes += 10;
    owner.stat.moveRes += 10;
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-10);
    owner.stat.attack -= 1;
    owner.stat.spell -= 1;
    owner.stat.speed -= 1;
    owner.stat.critical -= 10;
    owner.stat.multiply -= 10;
    owner.stat.neutRes -= 10;
    owner.stat.debuRes -= 10;
    owner.stat.moveRes -= 10;
  }
}
