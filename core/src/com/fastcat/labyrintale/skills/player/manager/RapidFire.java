package com.fastcat.labyrintale.skills.player.manager;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;

public class RapidFire extends AbstractSkill {

  private static final String ID = "RapidFire";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.ENEMY;
  private static final int VALUE = 1;

  public RapidFire(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(VALUE, 1);
  }

  @Override
  public void onTarget(AbstractEntity e) {
    top(new AttackAction(owner, e, attack, AttackAction.AttackType.LIGHT, true));
    top(new AttackAction(owner, e, attack, AttackAction.AttackType.LIGHT, true));
    top(new AttackAction(owner, e, attack, AttackAction.AttackType.LIGHT, true));
  }

  @Override
  public void use() {}

  @Override
  protected void upgradeCard() {}
}
