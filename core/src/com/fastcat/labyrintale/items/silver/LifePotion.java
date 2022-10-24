package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.ImmuneStatus;

public class LifePotion extends AbstractItem {

  private static final String ID = "LifePotion";
  private static final ItemRarity RARITY = ItemRarity.SILVER;

  public LifePotion(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.stat.debuRes += 10;
  }

  @Override
  public void onRemove() {
    owner.stat.debuRes -= 10;
  }

  @Override
  public void atBattleStart() {
    flash();
    bot(
            new ApplyStatusAction(
                    new ImmuneStatus(1), owner, owner, false));
  }
}
