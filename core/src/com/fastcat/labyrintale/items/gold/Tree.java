package com.fastcat.labyrintale.items.gold;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;

public class Tree extends AbstractItem {

  private static final String ID = "Tree";
  private static final ItemRarity RARITY = ItemRarity.GOLD;

  public Tree(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.modifyMaxHealth(10);
    owner.stat.critical += 30;
    owner.stat.debuRes += 5;
    owner.stat.neutRes += 5;
    owner.stat.moveRes += 5;
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-10);
    owner.stat.critical -= 30;
    owner.stat.debuRes -= 5;
    owner.stat.neutRes -= 5;
    owner.stat.moveRes -= 5;
  }
}
