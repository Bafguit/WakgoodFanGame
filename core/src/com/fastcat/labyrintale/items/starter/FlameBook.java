package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.BurnStatus;

public class FlameBook extends AbstractItem {

  private static final String ID = "FlameBook";
  private static final ItemRarity RARITY = ItemRarity.STARTER;

  public FlameBook(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void atBattleStart() {
    flash();
    bot(
        new ApplyStatusAction(
            new BurnStatus(1), owner, AbstractSkill.SkillTarget.ENEMY_FIRST_TWO, false));
  }
}
