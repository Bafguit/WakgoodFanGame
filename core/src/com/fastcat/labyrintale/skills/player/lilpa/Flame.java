package com.fastcat.labyrintale.skills.player.lilpa;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.BurnStatus;

public class Flame extends AbstractSkill {

  private static final String ID = "Flame";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.ENEMY_ALL;
  private static final int VALUE = 2;

  public Flame(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(VALUE, 1);
    setBaseValue(VALUE, 1);
    setBaseCost(2);
  }

  @Override
  public void use() {
    bot(new ApplyStatusAction(new BurnStatus(value), owner, target, true));
    bot(new AttackAction(owner, target, attack, AttackAction.AttackType.BURN, true));
  }

  @Override
  protected void upgradeCard() {}
}
