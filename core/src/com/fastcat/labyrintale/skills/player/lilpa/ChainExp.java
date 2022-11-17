package com.fastcat.labyrintale.skills.player.lilpa;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.*;

public class ChainExp extends AbstractSkill {

  private static final String ID = "ChainExp";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.ENEMY_ALL;
  private static final int VALUE = 2;

  public ChainExp(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(VALUE, 1);
    setBaseValue(VALUE, 1);
    setBaseValue2(3);
    setBaseCost(5);
  }

  @Override
  public void use() {
    for(int i = 0; i < value2; i++) {
      bot(new AttackAction(owner, target, attack, AttackAction.AttackType.BURN, true));
    }
    for(int i = 0; i < value2; i++) {
      bot(new ApplyStatusAction(new BurnStatus(value), owner, target, true));
      bot(new ApplyStatusAction(new ShockStatus(value), owner, target, true));
    }
  }

  @Override
  protected void upgradeCard() {}
}
