package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class FireStaff extends AbstractItem {

  private static final String ID = "FireStaff";
  private static final ItemRarity RARITY = ItemRarity.STARTER;

  public FireStaff(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void atBattleStart() {
    flash();
    bot(
        new ApplyStatusAction(
            new UnfortifiedStatus(1), owner, AbstractSkill.SkillTarget.ENEMY_FIRST, false));
  }
}
