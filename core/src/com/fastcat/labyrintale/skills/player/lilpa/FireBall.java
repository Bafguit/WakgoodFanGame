package com.fastcat.labyrintale.skills.player.lilpa;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.BurnStatus;
import com.fastcat.labyrintale.status.InfectionStatus;

public class FireBall extends AbstractSkill {

  private static final String ID = "FireBall";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.STARTER;
  private static final SkillTarget TARGET = SkillTarget.ENEMY;
  private static final int VALUE = 2;

  public FireBall(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(VALUE, 1);
    setBaseValue(VALUE);
  }

  @Override
  public void use() {}

  @Override
  public void onTarget(AbstractEntity e) {
    top(new ApplyStatusAction(new BurnStatus(value), owner, e, true));
    top(new AttackAction(owner, e, attack, AttackAction.AttackType.BURN, true));
  }

  @Override
  protected void upgradeCard() {}
}
