package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.CounterStatus;

public class CounterE extends AbstractSkill {

  private static final String ID = "CounterE";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.ENEMY;
  private static final SkillTarget TARGET = SkillTarget.SELF;
  private static final int VALUE = 2;

  public CounterE(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseValue(VALUE, 1);
  }

  @Override
  public void use() {
    bot(new ApplyStatusAction(new AttackStatus(value), owner, owner, true));
    bot(new ApplyStatusAction(new CounterStatus(value), owner, owner, true));
  }

  @Override
  protected void upgradeCard() {}
}
