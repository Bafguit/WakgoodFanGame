package com.fastcat.labyrintale.items.bronze;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.CourageStatus;

public class Shield extends AbstractItem {

  private static final String ID = "Shield";
  private static final ItemRarity RARITY = ItemRarity.BRONZE;

  public Shield(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.modifyMaxHealth(8);
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-8);
  }
}
