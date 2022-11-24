package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.LethargyStatus;

public class CrossPin extends AbstractItem {

  private static final String ID = "CrossPin";
  private static final ItemRarity RARITY = ItemRarity.SILVER;

  public CrossPin(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.stat.debuRes += 20;
    owner.stat.moveRes += 20;
  }

  @Override
  public void onRemove() {
    owner.stat.debuRes -= 20;
    owner.stat.moveRes -= 20;
  }
}
