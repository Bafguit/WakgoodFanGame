package com.fastcat.labyrintale.skills.player.lilpa;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.BurnStatus;

public class Ignite extends AbstractSkill {

  private static final String ID = "Ignite";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.ENEMY;
  private static final int VALUE = 4;

  public Ignite(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseValue(VALUE, 1);
    cost = 2;
  }

  @Override
  public void use() {}

  @Override
  public void onTarget(AbstractEntity e) {
    top(new ApplyStatusAction(new BurnStatus(value), owner, e, false));
  }

  @Override
  protected void upgradeCard() {}
}
