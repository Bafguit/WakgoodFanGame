package com.fastcat.labyrintale.skills.player.lilpa;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.BurnStatus;

public class FireBall extends AbstractSkill {

  private static final String ID = "FireBall";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.STARTER;
  private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST_TWO;
  private static final int VALUE = 2;

  public FireBall(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(VALUE, 1);
    setBaseValue(2);
  }

  @Override
  public void use() {
    AbstractAction a = new AttackAction(owner, target, attack, AttackAction.AttackType.BURN, true);
    bot(a);
    AbstractAction m = new ApplyStatusAction(new BurnStatus(value), owner, target, true);
    m.preAction = a;
    bot(m);
  }

  @Override
  protected void upgradeCard() {}
}
