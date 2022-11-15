package com.fastcat.labyrintale.skills.player.lilpa;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.ResistMinusStatus;
import com.fastcat.labyrintale.status.SpeedMinusStatus;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class ChainExp extends AbstractSkill {

  private static final String ID = "ChainExp";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.ENEMY_ALL;
  private static final int VALUE = 1;

  public ChainExp(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(VALUE);
    setBaseValue(1);
    setBaseValue2(2, 1);
    setBaseCost(3);
  }

  @Override
  public void use() {
    for(int i = 0; i < value2; i++) {
      bot(new AttackAction(owner, target, attack, AttackAction.AttackType.BURN));
    }
    for(int i = 0; i < value2; i++) {
      bot(new ApplyStatusAction(new UnfortifiedStatus(value), owner, target, true));
    }
  }

  @Override
  protected void upgradeCard() {}
}
