package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.BurnStatus;
import com.fastcat.labyrintale.status.ResistMinusStatus;

public class FlameBook extends AbstractItem {

  private static final String ID = "FlameBook";
  private static final ItemRarity RARITY = ItemRarity.STARTER;

  public FlameBook(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void atBattleStart() {
    top(
        new ApplyStatusAction(
            new ResistMinusStatus(1), owner, AbstractSkill.SkillTarget.ENEMY_ALL, true));
  }
}
