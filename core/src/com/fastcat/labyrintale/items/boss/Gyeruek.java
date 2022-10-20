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
    owner.modifyMaxHealth(5);
    owner.stat.attack += 1;
    owner.stat.spell += 1;
    owner.stat.speed += 1;
    owner.stat.critical += 0.05f;
    owner.stat.multiply += 0.05f;
    owner.stat.neutRes += 0.05f;
    owner.stat.debuRes += 0.05f;
    owner.stat.moveRes += 0.05f;
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-5);
    owner.stat.attack -= 1;
    owner.stat.spell -= 1;
    owner.stat.speed -= 1;
    owner.stat.critical -= 0.05f;
    owner.stat.multiply -= 0.05f;
    owner.stat.neutRes -= 0.05f;
    owner.stat.debuRes -= 0.05f;
    owner.stat.moveRes -= 0.05f;
  }
}
